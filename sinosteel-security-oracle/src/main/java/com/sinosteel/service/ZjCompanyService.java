package com.sinosteel.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinosteel.domain.User;
import com.sinosteel.domain.ZjCompany;
import com.sinosteel.domain.dictionary.ZjDictArea;
import com.sinosteel.domain.dictionary.ZjDictCommon;
import com.sinosteel.domain.elasticsearch.OracleToES;
import com.sinosteel.domain.thread.ZjCompanyThread;
import com.sinosteel.domain.thread.ZjPersonThread;
import com.sinosteel.framework.helpers.pagination.Pager;
import com.sinosteel.framework.utils.json.JsonUtil;
import com.sinosteel.repository.ZjCompanyRepository;
import com.sinosteel.repository.ZjDictAreaRepository;
import com.sinosteel.repository.ZjDictCommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class ZjCompanyService extends BaseService<ZjCompany> {

    @Autowired
    private ZjCompanyRepository zjCompanyRepository;
    @Autowired
    private ZjPersonService zjPersonService;
    @Autowired
    private ZjProductService zjProductService;
    @Autowired
    private ZjDictCommonRepository dictCommRepository;
    @Autowired
    private ZjDictAreaRepository dictAreaRepository;

    /**
     *  todo 企业查询
     * @param params
     * @return
     * @throws Exception
     */
    public JSONObject queryZjCompanys(final JSONObject params) throws Exception {
        // Pager: pageSize:分页    current:当前页码 ;分页采用 pring-data-jpa
        Pager pager = new Pager();
        if(params!=null && params.getJSONObject("page")!=null && !params.getJSONObject("page").isEmpty()){
            pager = JSONObject.toJavaObject(params.getJSONObject("page"), Pager.class);
        }
        Page<ZjCompany> resultPage = zjCompanyRepository.findAll(
                new Specification<ZjCompany>(){
                    @Override
                    public Predicate toPredicate(Root<ZjCompany> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                        // 对象属性
                        Path<String> companyName = root.get("companyName");
                        List<Predicate> list = new ArrayList<Predicate>();
                        if(params!=null && params.getString("name")!=null&&!"".equals(params.getString("name"))) {
                            // %% 查询效率慢临时单模糊匹配 list.add(cb.like(companyName,"%"+params.getString("name")+"%"));
                            list.add(cb.like(companyName,"%"+params.getString("name")+"%"));
                        }
                        Predicate[] p = new Predicate[list.size()];
                        return cb.and(list.toArray(p));
                    }
                }
                ,new PageRequest(pager.current-1,pager.pageSize, new Sort(Sort.Direction.DESC, "id")));
        JSONArray resultJSON=JsonUtil.toJSONArray(resultPage.getContent());
        /**
         *  Todo 对resultJSON 数据二次处理，将数据中涉及字典内容进行转化
         *  Todo
         */
        for(int i=0;i<resultJSON.size();i++){
            JSONObject job = resultJSON.getJSONObject(i);
            String jglx_code=job.getString("jglx")==null?"":job.getString("jglx");
            String jjlx_code=job.getString("jjlx")==null?"":job.getString("jjlx");
            String xzqh_code=job.getString("xzqh")==null?"":job.getString("xzqh");
            ZjDictCommon jglxDict=dictCommRepository.findByDicTypeAndCode("JGLX",jglx_code);
            String jglx_value = jglxDict==null?"":jglxDict.getCaption();
            ZjDictCommon jjlxDict=dictCommRepository.findByDicTypeAndCode("JJLX",jjlx_code);
            String jjlx_value = jjlxDict==null?"":jjlxDict.getCaption();
            ZjDictArea xzqhDict=dictAreaRepository.findByTypeAndCode("XZQH",xzqh_code);
            String xzqh_value = xzqhDict==null?"":xzqhDict.getValue();
            resultJSON.getJSONObject(i).put("jglxName",jglx_value);
            resultJSON.getJSONObject(i).put("jjlxName",jjlx_value);
            resultJSON.getJSONObject(i).put("xzqhName",xzqh_value);
        }
        JSONObject result = new JSONObject();
        result.put("total", resultPage.getTotalElements());
        result.put("data", resultJSON);
        return  result;

    }

    public JSONObject getZjCompanysByIdList(final JSONObject params, final List<ZjCompany> list) throws Exception {
        // Pager: pageSize:分页    current:当前页码 ;分页采用 pring-data-jpa
        Pager pager = new Pager();
        if(params!=null && params.getJSONObject("page")!=null && !params.getJSONObject("page").isEmpty()){
            pager = JSONObject.toJavaObject(params.getJSONObject("page"), Pager.class);
        }
        Page<ZjCompany> resultPage = zjCompanyRepository.findAll(
                new Specification<ZjCompany>(){
                    @Override
                    public Predicate toPredicate(Root<ZjCompany> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                        // 对象属性
                        Path<String> id = root.get("id");
                        List<Predicate> contentList = new ArrayList<Predicate>();
                        if(list!=null && !list.isEmpty()) {
                            CriteriaBuilder.In<String> in = cb.in(id);
                            for(ZjCompany company:list){
                                in.value(company.getId());
                            }
                            contentList.add(in);
                        }
                        Predicate[] p = new Predicate[contentList.size()];
                        return cb.and(contentList.toArray(p));
                    }
                }
                ,new PageRequest(pager.current-1,pager.pageSize, new Sort(Sort.Direction.DESC, "id")));
        JSONArray resultJSON=JsonUtil.toJSONArray(resultPage.getContent());
        /**
         *  Todo 对resultJSON 数据二次处理，将数据中涉及字典内容进行转化
         *  Todo
         */
        for(int i=0;i<resultJSON.size();i++){
            JSONObject job = resultJSON.getJSONObject(i);
            String jglx_code=job.getString("jglx")==null?"":job.getString("jglx");
            String jjlx_code=job.getString("jjlx")==null?"":job.getString("jjlx");
            String xzqh_code=job.getString("xzqh")==null?"":job.getString("xzqh");
            ZjDictCommon jglxDict=dictCommRepository.findByDicTypeAndCode("JGLX",jglx_code);
            String jglx_value = jglxDict==null?"":jglxDict.getCaption();
            ZjDictCommon jjlxDict=dictCommRepository.findByDicTypeAndCode("JJLX",jjlx_code);
            String jjlx_value = jjlxDict==null?"":jjlxDict.getCaption();
            ZjDictArea xzqhDict=dictAreaRepository.findByTypeAndCode("XZQH",xzqh_code);
            String xzqh_value = xzqhDict==null?"":xzqhDict.getValue();
            resultJSON.getJSONObject(i).put("jglxName",jglx_value);
            resultJSON.getJSONObject(i).put("jjlxName",jjlx_value);
            resultJSON.getJSONObject(i).put("xzqhName",xzqh_value);
        }
        JSONObject result = new JSONObject();
        result.put("total", resultPage.getTotalElements());
        result.put("data", resultJSON);
        return  result;

    }

    public JSONObject queryZjCompanysThread(final JSONObject params, final String startId, final String endId)
    {
        // Pager: pageSize:分页    current:当前页码 ;分页采用 pring-data-jpa
        Pager pager = new Pager();
        if(params!=null && params.getJSONObject("pagination")!=null && !params.getJSONObject("pagination").isEmpty()){
            pager = JSONObject.toJavaObject(params.getJSONObject("pagination"), Pager.class);
        }
        Page<ZjCompany> resultPage = zjCompanyRepository.findAll(
                new Specification<ZjCompany>(){
                    @Override
                    public Predicate toPredicate(Root<ZjCompany> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                        // 对象属性
                        Path<String> companyName = root.get("companyName");
                        Path<String> id = root.get("id");
                        List<Predicate> list = new ArrayList<Predicate>();
                        if(params!=null && params.getString("name")!=null&&!"".equals(params.getString("name"))) {
                            list.add(cb.like(companyName,"%"+params.getString("name")+"%"));
                        }
                        if(startId!=null && endId!=null){
                            String newEndId=String.valueOf(Long.valueOf(endId)-1);
                            list.add(cb.between(id,startId,newEndId));
                        }
                        Predicate[] p = new Predicate[list.size()];
                        return cb.and(list.toArray(p));
                    }
                }
                ,new PageRequest(pager.current-1,pager.pageSize, new Sort(Sort.Direction.DESC, "id")));
        JSONArray resultJSON=JsonUtil.toJSONArray(resultPage.getContent());
        JSONObject result = new JSONObject();
        result.put("total", resultPage.getTotalElements());
        result.put("data", resultJSON);
        return  result;

    }

    public JSONObject searchData(JSONObject params) throws Exception {
        //条件搜索企业信息
        JSONObject zjCompanysJSON=this.queryZjCompanys(params);
        //条件搜索人员信息
        JSONObject zjPersonsJSON=zjPersonService.queryZjPersons(params);
        JSONObject queryResultJson = new JSONObject();
        queryResultJson.put("companyData", zjCompanysJSON);
        queryResultJson.put("personData", zjPersonsJSON);
        return queryResultJson;
    }

     /*查询单个企业的所有信息*/
    public JSONObject searchCompanyAllInfo(JSONObject params) throws Exception {
        String zzjgdm=params.getString("zzjgdm")==null?"":params.getString("zzjgdm");
        String companyName=params.getString("companyName")==null?"":params.getString("companyName");
        //条件搜索企业信息
        JSON zjCompanyJSON= (JSON) JSON.toJSON(zjCompanyRepository.findByZzjgdm(zzjgdm));
        //条件搜索人员信息
        JSONArray zjPersonsJSON= JsonUtil.toJSONArray(zjPersonService.queryZjPersonByOrganName(params));
        //条件搜索产品
        JSONArray zjProductJSON= JsonUtil.toJSONArray(zjProductService.findByOrganName(params));
        JSONObject queryResultJson = new JSONObject();
        queryResultJson.put("companyData", zjCompanyJSON);
        queryResultJson.put("personData", zjPersonsJSON);
        queryResultJson.put("productData", zjProductJSON);
        return queryResultJson;
    }

    public JSONObject searchDataThread(JSONObject params) throws Exception {

        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        //创建多个有返回值的任务
        List<Future<JSONObject>> list = new ArrayList<Future<JSONObject>>();
        /*因企业数据量较大，开启多线程范围查找，最后汇总结果*/
        Long start = Long.valueOf(0);
        Long end = Long.valueOf(0);
        Long step = Long.valueOf(250000);
        for(int i=1;i<=10;i++){
            end = start + step;
            String startId=String.valueOf(start);
            String endId=String.valueOf(end);
            ZjCompanyThread zjCompanyThread=new ZjCompanyThread(params,this,startId,endId);
            //执行任务并获取Future对象
            Future<JSONObject> f1 = pool.submit(zjCompanyThread);
            list.add(f1);
            start = end;
        }
        ZjPersonThread zjPersonThread = new ZjPersonThread(params,zjPersonService);
        //执行任务并获取Future对象
        Future<JSONObject> f2 = pool.submit(zjPersonThread);
        list.add(f2);
        //关闭线程池
        pool.shutdown();
        JSONObject queryResultJson = new JSONObject();
        /*汇总数据*/
        Long sum = Long.valueOf(0);
        List<ZjCompany> sumList=new ArrayList<ZjCompany>();
        for (int i=0;i<10;i++){
            JSONObject listResult = list.get(i).get();
            sum=sum+Long.valueOf(listResult.get("total").toString());
            listResult.get("data");
            List<ZjCompany> temp=JSONArray.parseArray(listResult.get("data").toString(),ZjCompany.class);
            sumList.addAll(temp);
        }

        JSONObject companyData = new JSONObject();
        companyData.put("total",sum);
        companyData.put("data",JsonUtil.toJSONArray(sumList));
        queryResultJson.put("companyData",companyData);
        queryResultJson.put("personData", list.get(10).get());
        return queryResultJson;
    }


    public void addZjCompany( JSONObject params,User user)
    {
        ZjCompany zjCompany = JSONObject.toJavaObject(params, ZjCompany.class);
        this.saveEntity(zjCompany,user);
    }

    public void editZjCompany(JSONObject params, User user)
    {

        this.updateEntity(params,ZjCompany.class,user);
    }

    /**
     * todo 使用ES服务全文搜索企业信息
     * @param params
     * @return
     * @throws UnknownHostException
     */
    public JSONObject ESMatchByName(JSONObject params) throws Exception {
        //step 1.先从ES服务上查询企业信息
        String companyName=params.getString("name");
        OracleToES oes = new OracleToES();
        List<ZjCompany> list = oes.matchZjcompanyByName(companyName);
        //step 2.然后利用ES的数据进传统数据库二次查询企业信息
        JSONObject zjCompanysJSON = getZjCompanysByIdList(params,list);
        //条件搜索人员信息
        JSONObject zjPersonsJSON=zjPersonService.queryZjPersons(params);
        JSONObject queryResultJson = new JSONObject();
        queryResultJson.put("companyData", zjCompanysJSON);
        queryResultJson.put("personData", zjPersonsJSON);
        return queryResultJson;
    }

    /**
     * todo 根据ID范围获取数据，仅供抽取数据测试使用
     */
    public List<Object[]> findDataByRangeId() throws Exception {
        List<Object[]> resultPage = zjCompanyRepository.findDataByRangeId("2000001","2500000");
       return resultPage;
    }

}