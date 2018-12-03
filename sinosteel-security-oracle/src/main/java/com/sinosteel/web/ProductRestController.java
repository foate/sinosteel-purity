package com.sinosteel.web;

import com.sinosteel.framework.core.web.Request;
import com.sinosteel.framework.core.web.Response;
import com.sinosteel.framework.core.web.ResponseType;
import com.sinosteel.service.ZjProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController extends BaseController  {

    @Autowired
    private ZjProductService zjProductService;

    @RequestMapping(value = "/pcq/zjProducts")
    public Response queryZjProducts(Request request)
    {
        Response response = new Response();

        try
        {
            response.status = ResponseType.SUCCESS;
            //条件查询
            response.data = zjProductService.queryZjProducts(request.getParams());
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

    @RequestMapping(value = "/pcq/addZjProduct")
    public Response addZjProduct(Request request)
    {
        Response response = new Response();
        try
        {
            zjProductService.addZjProduct(request.getParams(),request.getUser());

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

    @RequestMapping(value = "/pcq/delZjProduct")
    public Response deleteZjProduct(Request request)
    {
        Response response = new Response();

        try
        {
            zjProductService.deleteEntity(request.getParams().getString("id"));
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

    @RequestMapping(value = "/pcq/editZjProduct")
    public Response editZjProduct(Request request)
    {
        Response response = new Response();

        try
        {
            zjProductService.editZjProduct(request.getParams(), request.getUser());

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
