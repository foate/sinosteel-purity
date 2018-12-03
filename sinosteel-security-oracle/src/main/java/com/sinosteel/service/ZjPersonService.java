package com.sinosteel.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinosteel.domain.User;
import com.sinosteel.domain.ZjCompany;
import com.sinosteel.domain.ZjPerson;
import com.sinosteel.framework.helpers.pagination.Pager;
import com.sinosteel.framework.utils.json.JsonUtil;
import com.sinosteel.repository.ZjCompanyRepository;
import com.sinosteel.repository.ZjPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZjPersonService extends BaseService<ZjPerson> {

    @Autowired
    private ZjPersonRepository zjPersonRepository;
    @Autowired
    private ZjCompanyRepository zjCompanyRepository;

    public JSONObject queryZjPersons(final JSONObject params)
    {
        // Pager: pageSize:分页    current:当前页码 ;分页采用 pring-data-jpa
        Pager pager = new Pager();
        if(params!=null && params.getJSONObject("page")!=null && !params.getJSONObject("page").isEmpty()){
            pager = JSONObject.toJavaObject(params.getJSONObject("page"), Pager.class);
        }
        /**
         *  关于排序问题，经过测试发现，oracle 数据库不能使用分页排序，
         *  这样数据会乱掉（不要使用 new PageRequest 方式）；
         *  采用方式：Specification 中使用 query.orderBy() 方法。
         *  这个问题应该是排序分页先后问题。
         */

        /*  new PageRequest 多条件组合排序 */
        /*Sort sort = new Sort(Sort.Direction.DESC, "birthday");
        sort.and(new Sort(Sort.Direction.ASC, "id"));*/
        Page<ZjPerson> resultPage = zjPersonRepository.findAll(
                new Specification<ZjPerson>(){
                    @Override
                    public Predicate toPredicate(Root<ZjPerson> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                        // 对象属性
                        Path<String> name = root.get("name");
                        List<Predicate> list = new ArrayList<Predicate>();
                        if(params!=null && params.getString("name")!=null&&!"".equals(params.getString("name"))) {
                            list.add(cb.like(name,params.getString("name")+"%"));
                        }
                        /* 排序  */
                        query.orderBy(cb.asc(root.get("birthday")),cb.asc(root.get("id")));
                        Predicate[] p = new Predicate[list.size()];
                        return cb.and(list.toArray(p));

                    }
                }
                ,new PageRequest(pager.current-1,pager.pageSize/*,sort*/));
        JSONArray resultJSON=JsonUtil.toJSONArray(resultPage.getContent());
        JSONObject result = new JSONObject();
        result.put("total", resultPage.getTotalElements());
        result.put("data", resultJSON);
        return  result;

    }

    public JSONObject searchZjPersonAllInfo( JSONObject params) throws Exception {
        String id = params.getString("id")==null?"":params.getString("id");
        String cardId=params.getString("cardId")==null?"":params.getString("cardId");
        ZjPerson zjPerson = zjPersonRepository.findById(id);
        /**
         * 查询跟人员有关系的企业时，因数据问题，
         * 一方面是根据人员身份证号，去企业表中查询相关的企业；
         * 另一个方面根据人员表中的企业信息去匹配查询。
         */
        String searchCard = zjPerson.getZjhm()==null?"":zjPerson.getZjhm();
        String searchCompanyName = zjPerson.getOrganName()==null?"":zjPerson.getOrganName();
        List<ZjCompany> companys = zjCompanyRepository.findByLegalPersonNo(searchCard);
        List<ZjCompany> companys_other=zjCompanyRepository.findByCompanyName(searchCompanyName);
        companys=companys==null?new ArrayList<ZjCompany>():companys;
        companys.addAll(companys_other==null?new ArrayList<ZjCompany>():companys_other);
        JSONArray companyResult=JsonUtil.toJSONArray(companys);
        JSONObject result = new JSONObject();
        result.put("personData",JSONArray.toJSON(zjPerson));
        result.put("companyData",companyResult);
        return result;
    }

    public ZjPerson queryZjPersonByCardId( JSONObject params,User user) throws Exception {
        String cardId=params.getString("cardId")==null?"":params.getString("cardId");
       return zjPersonRepository.findByZjhm(cardId);
    }

    public List<ZjPerson> queryZjPersonByOrganName( JSONObject params) throws Exception {
        String organName=params.getString("companyName")==null?"":params.getString("companyName");
        return zjPersonRepository.findByOrganName(organName);
    }

    public void addZjPerson( JSONObject params,User user)
    {
        ZjPerson zjPerson = JSONObject.toJavaObject(params, ZjPerson.class);
        this.saveEntity(zjPerson,user);
    }

    public void editZjPerson(JSONObject params, User user)
    {

        this.updateEntity(params,ZjPerson.class,user);
    }

}