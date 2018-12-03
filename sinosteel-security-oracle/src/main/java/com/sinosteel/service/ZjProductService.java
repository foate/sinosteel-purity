package com.sinosteel.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinosteel.domain.User;
import com.sinosteel.domain.ZjProduct;
import com.sinosteel.framework.helpers.pagination.Pager;
import com.sinosteel.framework.utils.json.JsonUtil;
import com.sinosteel.repository.ZjProductRepository;
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
public class ZjProductService extends BaseService<ZjProduct> {

    @Autowired
    private ZjProductRepository zjProductRepository;


    public JSONObject queryZjProducts(final JSONObject params)
    {
        // Pager: pageSize:分页    current:当前页码 ;分页采用 pring-data-jpa
        Pager pager = new Pager();
        if(params!=null && params.getJSONObject("pagination")!=null && !params.getJSONObject("pagination").isEmpty()){
            pager = JSONObject.toJavaObject(params.getJSONObject("pagination"), Pager.class);
        }
        Page<ZjProduct> resultPage = zjProductRepository.findAll(
                new Specification<ZjProduct>(){
                    @Override
                    public Predicate toPredicate(Root<ZjProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                        // 对象属性
                        Path<String> productName = root.get("productName");
                        List<Predicate> list = new ArrayList<Predicate>();
                        if(params!=null && params.getString("name")!=null&&!"".equals(params.getString("name"))) {
                            list.add(cb.like(productName,"%"+params.getString("name")+"%"));
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

    public List<ZjProduct> findByProductName( JSONObject params) throws Exception {
        String productName=params.getString("productName")==null?"":params.getString("productName");
       return zjProductRepository.findByProductName(productName);
    }

    public List<ZjProduct> findByOrganName( JSONObject params) throws Exception {
        String organName=params.getString("companyName")==null?"":params.getString("companyName");
        return zjProductRepository.findByOrganName(organName);
    }

    public List<ZjProduct> findByZzjgdm( JSONObject params,User user) throws Exception {
        String zzjgdm=params.getString("zzjgdm")==null?"":params.getString("zzjgdm");
        return zjProductRepository.findByZzjgdm(zzjgdm);
    }

    public void addZjProduct( JSONObject params,User user)
    {
        ZjProduct zjProduct = JSONObject.toJavaObject(params, ZjProduct.class);
        this.saveEntity(zjProduct,user);
    }

    public void editZjProduct(JSONObject params, User user)
    {
        this.updateEntity(params,ZjProduct.class,user);
    }

}