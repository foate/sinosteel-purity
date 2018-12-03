package com.sinosteel.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.sinosteel.domain.*;
import com.sinosteel.framework.helpers.pagination.PageResult;
import com.sinosteel.framework.helpers.pagination.Pager;
import com.sinosteel.framework.utils.json.JsonUtil;
import com.sinosteel.framework.utils.string.StringUtil;
import com.sinosteel.repository.IndustryCompanyRepository;
import org.hibernate.jpa.criteria.path.RootImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.awt.print.Pageable;
import java.util.*;

@Service
public class IndustryCompanyService extends BaseService<IndustryCompany> {

    @Autowired
    private IndustryCompanyRepository industryCompanyRepository;



    public JSONObject queryIndustryCompanys(final JSONObject params)
    {
        // Pager: pageSize:分页    current:当前页码 ;分页采用 pring-data-jpa
        Pager pager = new Pager();
        if(params!=null && params.getJSONObject("pagination")!=null && !params.getJSONObject("pagination").isEmpty()){
            pager = JSONObject.toJavaObject(params.getJSONObject("pagination"), Pager.class);
        }
        Page<IndustryCompany> resultPage = industryCompanyRepository.findAll(
                new Specification<IndustryCompany>(){
                    @Override
                    public Predicate toPredicate(Root<IndustryCompany> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                        // 对象属性
                        Path<String> orgName = root.get("orgName");
                        List<Predicate> list = new ArrayList<Predicate>();
                        if(params!=null && params.getString("name")!=null&&!"".equals(params.getString("name"))) {
                            list.add(cb.like(orgName,"%"+params.getString("name")+"%"));
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

    public void addIndustryCompany( JSONObject params,User user)
    {
        IndustryCompany industryCompany = JSONObject.toJavaObject(params, IndustryCompany.class);
        this.saveEntity(industryCompany,user);
    }

    public void editIndustryCompany(JSONObject params, User user)
    {

        this.updateEntity(params,IndustryCompany.class,user);
    }

}