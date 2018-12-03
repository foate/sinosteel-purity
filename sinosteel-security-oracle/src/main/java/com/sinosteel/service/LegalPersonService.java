package com.sinosteel.service;

import com.alibaba.fastjson.JSONObject;
import com.sinosteel.domain.LegalPerson;
import com.sinosteel.domain.User;
import com.sinosteel.framework.helpers.pagination.PageResult;
import com.sinosteel.framework.helpers.pagination.Pager;
import com.sinosteel.framework.utils.string.StringUtil;
import com.sinosteel.repository.LegalPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class LegalPersonService extends BaseService<LegalPerson> {

    @Autowired
    private LegalPersonRepository legalPersonRepository;



    public JSONObject queryLegalPersons(JSONObject params)
    {
        StringBuilder hqlBuilder = new StringBuilder("FROM LegalPerson person WHERE 1 = 1 ");
        HashMap<String, Object> paramsMap = new HashMap<String, Object>();

        if(params != null)
        {
            String id = params.getString("id");
            if(!StringUtil.isEmpty(id))
            {
                hqlBuilder.append("AND person.id =:id ");
                paramsMap.put("id", id);
            }

            String name = params.getString("name");
            if(!StringUtil.isEmpty(name))
            {
                hqlBuilder.append("AND person.name like :name ");
                paramsMap.put("name", "%"+name+"%");
            }

            String sex = params.getString("sex");
            if(!StringUtil.isEmpty(sex)){
                hqlBuilder.append("AND person.sex=:sex ");
                paramsMap.put("sex",sex);
            }

            String nation = params.getString("nation");
            if(!StringUtil.isEmpty(nation)){
                hqlBuilder.append("AND person.nation=:nation ");
                paramsMap.put("nation",nation);
            }

            String persionId = params.getString("persionId");
            if(!StringUtil.isEmpty(persionId)){
                hqlBuilder.append("AND person.persionId=:persionId ");
                paramsMap.put("persionId",persionId);
            }

            String education = params.getString("education");
            if(!StringUtil.isEmpty(education)){
                hqlBuilder.append("AND person.education=:education ");
                paramsMap.put("education",education);
            }
            Date birthday = params.getDate("birthday");
            if(birthday!=null){
                hqlBuilder.append("AND person.birthday=:birthday ");
                paramsMap.put("birthday",birthday);
            }

        }
        hqlBuilder.append("ORDER BY CREATED_TIME DESC ");

        Pager pager = JSONObject.toJavaObject(params.getJSONObject("pagination"), Pager.class);
        PageResult<LegalPerson> pageResult = legalPersonRepository.executeHql(hqlBuilder.toString(), paramsMap, pager);

        return pageResult.toJSONObject();
    }

    public void addLegalPerson(JSONObject params, User user)
    {
        LegalPerson person = JSONObject.toJavaObject(params, LegalPerson.class);
        this.saveEntity(person,user);
        //this.saveEntity(params,Company.class,user);
    }

    public void editLegalPerson(JSONObject params, User user)
    {
        this.updateEntity(params,LegalPerson.class,user);
    }
}
