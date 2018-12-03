package com.sinosteel.domain.elasticsearch;

import com.alibaba.fastjson.JSONArray;
import com.sinosteel.domain.ZjCompany;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class OracleToES {

    private static TransportClient client;
    //设置集群名称
    private static Settings settings = Settings.builder().put("cluster.name", "my-application").build();// 集群名
    //创建client
    static {
        try {
            client = new PreBuiltTransportClient(settings)
                            .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private final static String zjcompany="zjcompany";
    private final static String name="name";


    /**
     * todo 初始化客户端
     */
    public void getClient() throws UnknownHostException {
        //设置集群名称
        Settings settings = Settings.builder().put("cluster.name", "my-application").build();// 集群名
        //创建client
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

    }

    /**
     * 创建索引并添加映射
     * @throws
     */
    public void createIndexAndMapping() throws Exception{

        CreateIndexRequestBuilder cib=client.admin().indices().prepareCreate(zjcompany);
        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("properties") //设置之定义字段
                .startObject("companyId")
                .field("type","text") //设置数据类型
                .endObject()
                .startObject("companyName")
                .field("type","text")
                // 使用 IK 分词
                .field("analyzer","ik_max_word")
                .field("search_analyzer","ik_max_word")
                .endObject()
                .endObject()
                .endObject();
        cib.addMapping(name, mapping);
        CreateIndexResponse res=cib.execute().actionGet();
        System.out.println("----------添加映射成功----------");
    }

    /**
     *  创建索引并添加文档
     * @throws Exception
     */
    public void addIndexAndDocument() throws Exception{

        Date time = new Date();
        IndexResponse response = client.prepareIndex(zjcompany,name)
                .setSource(XContentFactory.jsonBuilder().startObject()
                        .field("id","1")
                        .field("companyId","928032")
                        .field("companyName","潍柴动力股份有限公司")
                        .endObject())
                .get();
        System.out.println("添加索引成功,版本号："+response.getVersion());
    }
    //手动 批量更新
    public void multipleBulkProcessor() throws Exception {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        for(int i=1;i<2;i++){
            //业务对象
            Map<String, Object> source = new HashMap<String, Object>();
            source.put("companyId", "1466681");
            source.put("companyName", "潍柴动力液压科技有限公司");
            /**
             * todo elasticSearch 新版本(6.5.1) 支持 map 方式
             * String jsons = "{\"companyId\":\"928032\",\"companyName\":\"潍柴动力股份有限公司\"}";
             */

            IndexRequestBuilder indexRequest = client.prepareIndex(zjcompany, name)
                    //指定不重复的ID
                    .setSource(source).setId(String.valueOf(2));
            //添加到builder中
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
            System.out.println(bulkResponse.buildFailureMessage());
        }else{
            System.out.println("创建成功!!!");
        }
    }

    public void putDataToES(List<Object[]> list) throws Exception {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        for(int i=0;i<list.size();i++){
            Object[] zjCompany=list.get(i);
            //业务对象
            Map<String, Object> source = new HashMap<String, Object>();
            source.put("companyId", zjCompany[0]);
            source.put("companyName",zjCompany[1]);
            /**
             * todo elasticSearch 新版本(6.5.1) 支持 map 方式
             * String jsons = "{\"companyId\":\"928032\",\"companyName\":\"潍柴动力股份有限公司\"}";
             */

            IndexRequestBuilder indexRequest = client.prepareIndex(zjcompany, name)
                    //指定不重复的ID
                    .setSource(source).setId(String.valueOf(i+2000100));
            //添加到builder中
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
            System.out.println(bulkResponse.buildFailureMessage());
        }else{
            System.out.println("创建成功!!!");
        }
    }

    public List<ZjCompany> matchZjcompanyByName(String name) {
        // todo 在prepareSearch()的参数为索引库列表，意为要从哪些索引库中进行查询
        SearchResponse response = client.prepareSearch(zjcompany)
                // todo  设置查询类型，有QUERY_AND_FETCH  QUERY_THEN_FETCH  DFS_QUERY_AND_FETCH  DFS_QUERY_THEN_FETCH
                .setSearchType(SearchType.DEFAULT)
                .setQuery(QueryBuilders.matchQuery("companyName",name))
                .get();
        SearchHits searchHits = response.getHits();
        SearchHit[] hits = searchHits.getHits();    // 查询结果
        List<ZjCompany> zjCompanyList = new ArrayList<ZjCompany>();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
            Map<String,Object> map = hit.getSourceAsMap();
            ZjCompany company = new ZjCompany();
            company.setId(hit.getSourceAsMap().get("companyId").toString());
            company.setCompanyName(hit.getSourceAsMap().get("companyName").toString());
            zjCompanyList.add(company);
        }
        return zjCompanyList;
    }

    public void search() {
        // todo 在prepareSearch()的参数为索引库列表，意为要从哪些索引库中进行查询
        SearchResponse response = client.prepareSearch(zjcompany)
                // todo  设置查询类型，有QUERY_AND_FETCH  QUERY_THEN_FETCH  DFS_QUERY_AND_FETCH  DFS_QUERY_THEN_FETCH
                .setSearchType(SearchType.DEFAULT)
                //.setQuery(QueryBuilders.prefixQuery("content", "烂摊子"))
                // 设置相应的query，用于检索，termQuery的参数说明：name是doc中的具体的field，value就是要找的具体的值
                //.setQuery(QueryBuilders.regexpQuery("content", ".*烂摊子.*"))
                //.setQuery(QueryBuilders.prefixQuery("companyName", "潍柴"))
                .setQuery(QueryBuilders.matchQuery("companyName","动力"))
                .get();
        showResult(response);
    }

    /**
     * 格式化输出查询结果
     * @param response
     */
    private void showResult(SearchResponse response) {
        SearchHits searchHits = response.getHits();
        float maxScore = searchHits.getMaxScore();  // 查询结果中的最大文档得分
        System.out.println("maxScore: " + maxScore);
        long totalHits = searchHits.getTotalHits(); // 查询结果记录条数
        System.out.println("totalHits: " + totalHits);
        SearchHit[] hits = searchHits.getHits();    // 查询结果
        System.out.println("当前返回结果记录条数：" + hits.length);
        for (SearchHit hit : hits) {
            long version = hit.getVersion();
            String id = hit.getId();
            String index = hit.getIndex();
            String type = hit.getType();
            float score = hit.getScore();
            System.out.println("===================================================");
            String source = hit.getSourceAsString();
            System.out.println("version: " + version);
            System.out.println("id: " + id);
            System.out.println("index: " + index);
            System.out.println("type: " + type);
            System.out.println("score: " + score);
            System.out.println("source: " + source);
        }
    }

/*
 public static void  main (String[] args) throws Exception {

        OracleToES oes = new OracleToES();
        //oes.createIndexAndMapping();
        //oes.multipleBulkProcessor();
        //oes.search();

    }
    */
}
