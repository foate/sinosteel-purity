package com.sinosteel.web;

import com.alibaba.fastjson.JSONObject;
import com.sinosteel.framework.core.web.Request;
import com.sinosteel.framework.core.web.Response;
import com.sinosteel.framework.core.web.ResponseType;
import com.sinosteel.framework.utils.json.JsonUtil;
import com.sinosteel.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyRestController extends BaseController  {

    @Autowired
    private CompanyService companyService;


    @RequestMapping(value = "/pcq/companys")
    public Response queryCompanys(Request request)
    {
        Response response = new Response();

        try
        {
            response.status = ResponseType.SUCCESS;
            if(request.getParams()==null || request.getParams().isEmpty()){
                //全部查询
                response.data = JsonUtil.toJSONArray(companyService.findAll());
            }else {
                //条件查询
                response.data = companyService.queryCompanys(request.getParams());
            }
            response.message = "";
        }
        catch(Exception e)
        {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    /**
     * 综合信息查询
     * @param request
     * @return
     */
    @RequestMapping(value = "/pcq/searchData")
    public Response searchData(Request request)
    {
        Response response = new Response();

        try
        {
            response.status = ResponseType.SUCCESS;
            if(request.getParams()==null || request.getParams().isEmpty()){
                //全部查询
                response.data = companyService.searchDataAll();
            }else {
                //条件查询
                response.data = companyService.searchData(request.getParams());
            }
            response.message = "";
        }
        catch(Exception e)
        {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/pcq/addCompany")
    public Response addCompany(Request request)
    {
        Response response = new Response();
        try
        {
            companyService.addCompany(request.getParams(),request.getUser());

            response.status = ResponseType.SUCCESS;
        }
        catch(Exception e)
        {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/pcq/delCompany")
    public Response deleteCompany(Request request)
    {
        Response response = new Response();

        try
        {
            companyService.deleteEntity(request.getParams().getString("id"));
            response.status = ResponseType.SUCCESS;
        }
        catch(Exception e)
        {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/pcq/editCompany")
    public Response editCompany(Request request)
    {
        Response response = new Response();

        try
        {
            companyService.editCompany(request.getParams(), request.getUser());

            response.status = ResponseType.SUCCESS;
        }
        catch(Exception e)
        {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

}
