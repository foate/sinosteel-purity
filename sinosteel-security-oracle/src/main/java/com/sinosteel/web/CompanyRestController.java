package com.sinosteel.web;

import com.alibaba.fastjson.JSON;
import com.sinosteel.domain.User;
import com.sinosteel.domain.ZjCompany;
import com.sinosteel.domain.elasticsearch.OracleToES;
import com.sinosteel.framework.core.auth.AuthorizationScope;
import com.sinosteel.framework.core.auth.RequiresAuthorization;
import com.sinosteel.framework.core.web.Request;
import com.sinosteel.framework.core.web.RequestType;
import com.sinosteel.framework.core.web.Response;
import com.sinosteel.framework.core.web.ResponseType;
import com.sinosteel.framework.utils.json.JsonUtil;
import com.sinosteel.repository.ZjCompanyRepository;
import com.sinosteel.service.CompanyService;
import com.sinosteel.service.IndustryCompanyService;
import com.sinosteel.service.UserService;
import com.sinosteel.service.ZjCompanyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CompanyRestController extends BaseController  {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private IndustryCompanyService industryCompanyService;
    @Autowired
    private ZjCompanyService zjCompanyService;
    @Autowired
    private ZjCompanyRepository zjCompanyRepository;

    /*@RequiresAuthorization(
            requestType = RequestType.QUERY,
            serviceClass = UserService.class,
            editScope = AuthorizationScope.USER,
            deleteScope = AuthorizationScope.USER)*/
    @RequestMapping(value = "/pcq/companys")
    @RequiresPermissions(value = "editProject")
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

    @RequestMapping(value = "/pcq/industryCompanys")
    public Response queryIndustryCompanys(Request request)
    {
        Response response = new Response();

        try
        {
            response.status = ResponseType.SUCCESS;
            //条件查询
            response.data = industryCompanyService.queryIndustryCompanys(request.getParams());
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

    @RequestMapping(value = "/pcq/zjCompanys")
    public Response queryZjCompanys(Request request)
    {
        Response response = new Response();

        try
        {
            response.status = ResponseType.SUCCESS;
            //条件查询
            response.data = zjCompanyService.queryZjCompanys(request.getParams());
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
     * 综合信息查询测试功能
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

    /**
     * 综合信息查询
     * @param request
     * @return
     */
    @RequestMapping(value = "/search/bigData")
    public Response bigData(Request request)
    {
        Response response = new Response();

        try
        {
            Long start = System.currentTimeMillis();
            response.status = ResponseType.SUCCESS;
            response.data = zjCompanyService.searchData(request.getParams());
            Long end = System.currentTimeMillis();
            BigDecimal time = new BigDecimal(end-start);
            BigDecimal div = new BigDecimal(1000);
            String message = "使用传统关系数据库模糊查询,执行时间为: "+time.divide(div, 3, BigDecimal.ROUND_HALF_UP)+"秒";
            System.out.println(message);
            response.message = message;
        }
        catch(Exception e)
        {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/search/bigDataThread")
    public Response bigDataThread(Request request)
    {
        Response response = new Response();

        try
        {
            response.status = ResponseType.SUCCESS;
            response.data = zjCompanyService.searchDataThread(request.getParams());
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

    @RequestMapping(value = "/search/zjCompany")
    public Response zjCompany(Request request)
    {
        Response response = new Response();

        try
        {
            response.status = ResponseType.SUCCESS;
            String id=request.getParams().getString("id");
            response.data = (JSON) JSON.toJSON(zjCompanyRepository.findById(id));
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

    @RequestMapping(value = "/search/zjCompanyAllInfo")
    public Response zjCompanyAllInfo(Request request)
    {
        Response response = new Response();

        try
        {
            response.status = ResponseType.SUCCESS;
            response.data = zjCompanyService.searchCompanyAllInfo(request.getParams());
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

    /* todo  elasticsearch 全文检索 */

    /**
     * todo 使用ES服务快速检索企业信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/search/ESMatch")
    public Response ESMatch(Request request)
    {
        Response response = new Response();
        try
        {
            Long start = System.currentTimeMillis();
            response.status = ResponseType.SUCCESS;
            response.data = zjCompanyService.ESMatchByName(request.getParams());
            Long end = System.currentTimeMillis();
            BigDecimal time = new BigDecimal(end-start);
            BigDecimal div = new BigDecimal(1000);
            String message = "使用ES服务+传统关系数据库模糊查询，执行时间为: "+time.divide(div, 3, BigDecimal.ROUND_HALF_UP)+"秒";
            System.out.println(message);
            response.message = message;
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
     * todo 从数据库中获取数据导入elasticsearch
     * @param request
     * @return
     */
    @RequestMapping(value = "/search/putDataToES")
    public Response putDataToES(Request request)
    {
        Response response = new Response();

        try
        {
            response.status = ResponseType.SUCCESS;
            List<Object[]> list =zjCompanyService.findDataByRangeId();
            System.out.println(list.size());
            OracleToES oes = new OracleToES();
            oes.getClient();
            oes.putDataToES(list);
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


}
