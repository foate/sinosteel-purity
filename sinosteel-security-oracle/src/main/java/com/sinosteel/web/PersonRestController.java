package com.sinosteel.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinosteel.framework.core.web.Request;
import com.sinosteel.framework.core.web.Response;
import com.sinosteel.framework.core.web.ResponseType;
import com.sinosteel.framework.utils.json.JsonUtil;
import com.sinosteel.repository.ZjPersonRepository;
import com.sinosteel.service.ZjPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonRestController extends BaseController  {

    @Autowired
    private ZjPersonService zjPersonService;
    @Autowired
    private ZjPersonRepository zjPersonRepository;

    @RequestMapping(value = "/pcq/zjPersons")
    public Response queryZjPersons(Request request)
    {
        Response response = new Response();

        try
        {
            response.status = ResponseType.SUCCESS;
            //条件查询
            response.data = zjPersonService.queryZjPersons(request.getParams());
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
    @RequestMapping(value = "/search/zjPerson")
    public Response zjPerson(Request request)
    {
        Response response = new Response();

        try
        {
            response.status = ResponseType.SUCCESS;
            response.data = zjPersonService.searchZjPersonAllInfo(request.getParams());
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
    @RequestMapping(value = "/pcq/addZjPerson")
    public Response addZjPerson(Request request)
    {
        Response response = new Response();
        try
        {
            zjPersonService.addZjPerson(request.getParams(),request.getUser());

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

    @RequestMapping(value = "/pcq/delZjPerson")
    public Response deleteZjperson(Request request)
    {
        Response response = new Response();

        try
        {
            zjPersonService.deleteEntity(request.getParams().getString("id"));
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

    @RequestMapping(value = "/pcq/editZjPerson")
    public Response editZjPerson(Request request)
    {
        Response response = new Response();

        try
        {
            zjPersonService.editZjPerson(request.getParams(), request.getUser());

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
