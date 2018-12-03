package com.sinosteel.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinosteel.domain.Company;
import com.sinosteel.domain.LegalPerson;
import com.sinosteel.domain.User;
import com.sinosteel.framework.helpers.pagination.PageResult;
import com.sinosteel.framework.helpers.pagination.Pager;
import com.sinosteel.framework.utils.json.JsonUtil;
import com.sinosteel.framework.utils.string.StringUtil;
import com.sinosteel.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class CompanyService extends BaseService<Company> {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private LegalPersonService legalPersonService;


    public JSONObject queryCompanys(JSONObject params)
    {
        StringBuilder hqlBuilder = new StringBuilder("FROM Company company WHERE 1 = 1 ");
        HashMap<String, Object> paramsMap = new HashMap<String, Object>();

        if(params != null)
        {
            String id = params.getString("id");
            if(!StringUtil.isEmpty(id))
            {
                hqlBuilder.append("AND company.id =:id ");
                paramsMap.put("id", id);
            }

            String name = params.getString("name");
            if(!StringUtil.isEmpty(name))
            {
                hqlBuilder.append("AND company.name like :name ");
                paramsMap.put("name", "%"+name+"%");
            }
        }

        hqlBuilder.append("ORDER BY CREATED_TIME DESC ");

        Pager pager = JSONObject.toJavaObject(params.getJSONObject("pagination"), Pager.class);
        PageResult<Company> pageResult = companyRepository.executeHql(hqlBuilder.toString(), paramsMap, pager);
        return pageResult.toJSONObject();
    }

    public void addCompany( JSONObject params,User user)
    {
        /*
            获取企业人员集合（jsonArray）
            String persons=params.getString("persons");
        */
        JSONArray personArray=params.getJSONArray("persons");
        //参数params将人员信息部分删掉（保留企业信息
        params.remove("persons");
        Company company = JSONObject.toJavaObject(params, Company.class);
        //json数组转为List
        if(!personArray.isEmpty()){
            List<LegalPerson> legalPersons=personArray.toJavaList(LegalPerson.class);
            for(LegalPerson person:legalPersons){
                person.setId(UUID.randomUUID().toString());
                company.addPerson(person);
            }
            /*
            for(int i=0;i<personArray.size();i++){
                JSONObject obj=personArray.getJSONObject(i);
                LegalPerson person=obj.toJavaObject(LegalPerson.class);
                person.setId(UUID.randomUUID().toString());
               company.addPerson(person);
            }
            */
        }
        this.saveEntity(company,user);
        //this.saveEntity(params,Company.class,user);
    }

    public void editCompany(JSONObject params, User user)
    {
/*
        Company company = JSONObject.toJavaObject(params, Company.class);

        LegalPerson person = companyRepository.findOne(company.getId()).getPersons().get(0);
        company.addPerson(person);
*/
        this.updateEntity(params,Company.class,user);

    }


    public JSONObject searchDataAll()
    {
        //企业信息
        JSONArray companyJSON=JsonUtil.toJSONArray(this.findAll());
        //人员信息
        JSONArray legalPersonJSON=JsonUtil.toJSONArray(legalPersonService.findAll());
        JSONObject queryResult_company = new JSONObject();
        queryResult_company.put("total", companyJSON.size());
        queryResult_company.put("data", companyJSON);
        JSONObject queryResult_person = new JSONObject();
        queryResult_person.put("total", legalPersonJSON.size());
        queryResult_person.put("data", legalPersonJSON);
        JSONObject queryResultJson = new JSONObject();
        queryResultJson.put("companyData", queryResult_company);
        queryResultJson.put("personData", queryResult_person);
        return queryResultJson;
    }

    public JSONObject searchData(JSONObject params)
    {
        //条件搜索企业信息
        JSONObject companyJSON=this.queryCompanys(params);
        //条件搜索人员信息
        JSONObject legalPersonJSON=legalPersonService.queryLegalPersons(params);
        JSONObject queryResultJson = new JSONObject();
        queryResultJson.put("companyData", companyJSON);
        queryResultJson.put("personData", legalPersonJSON);
        return queryResultJson;
    }

}