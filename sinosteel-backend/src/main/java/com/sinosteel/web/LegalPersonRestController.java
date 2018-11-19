package com.sinosteel.web;

import com.sinosteel.framework.core.web.Request;
import com.sinosteel.framework.core.web.Response;
import com.sinosteel.framework.core.web.ResponseType;
import com.sinosteel.framework.utils.json.JsonUtil;
import com.sinosteel.service.LegalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LegalPersonRestController extends BaseController  {

    @Autowired
    private LegalPersonService legalPersonService;


    @RequestMapping(value = "/pcq/legalPersons")
    public Response queryLegalPersons(Request request)
    {
        Response response = new Response();

        try
        {
            response.status = ResponseType.SUCCESS;
            if(request.getParams()==null || request.getParams().isEmpty()){
                //全部查询
                response.data = JsonUtil.toJSONArray(legalPersonService.findAll());
            }else {
                //条件查询
                response.data = legalPersonService.queryLegalPersons(request.getParams());
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

    @RequestMapping(value = "/pcq/addLegalPerson")
    public Response addLegalPerson(Request request)
    {
        Response response = new Response();

        try
        {
            legalPersonService.addLegalPerson(request.getParams(), request.getUser());

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

    @RequestMapping(value = "/pcq/delLegalPerson")
    public Response deleteLegalPerson(Request request)
    {
        Response response = new Response();

        try
        {
            legalPersonService.deleteEntity(request.getParams().getString("id"));
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

    @RequestMapping(value = "/pcq/editLegalPerson")
    public Response editLegalPerson(Request request)
    {
        Response response = new Response();

        try
        {
            legalPersonService.editLegalPerson(request.getParams(), request.getUser());

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
