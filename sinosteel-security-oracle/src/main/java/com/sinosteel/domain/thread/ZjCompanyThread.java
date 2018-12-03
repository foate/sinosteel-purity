package com.sinosteel.domain.thread;

import com.alibaba.fastjson.JSONObject;
import com.sinosteel.service.ZjCompanyService;
import org.apache.log4j.Logger;

import java.util.concurrent.Callable;


/**
 * @Description:
 * @Company: CVICSE BigData
 * @Auther: peng_cqing
 * @Created: 2018/11/26 11:58
 */
public class ZjCompanyThread implements Callable<JSONObject> {
    //日志
    private static Logger logger = Logger.getLogger(ZjCompanyThread.class);
    private JSONObject params; // 数据参数
    private JSONObject result; // 数据结果
    private String startId; // 数据范围开始
    private String endId; // 数据范围结束
    private ZjCompanyService zjCompanyService;


    public ZjCompanyThread( JSONObject params,ZjCompanyService zjCompanyService,String startId,String endId) {
        this.params = params;
        this.zjCompanyService=zjCompanyService;
        this.startId=startId;
        this.endId=endId;
    }

    @Override
    public JSONObject call() throws Exception {
        /**
         * 功能描述:
         *  因数据量较大，单线程模糊查询很慢，
         *  改进方案：根据ID（序列生成）划分多个范围进行查找，最后汇总结果
         */
        //result=zjCompanyService.queryZjCompanys(params);
        result=zjCompanyService.queryZjCompanysThread(params,startId,endId);
        setResult(result);
        return result;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public String getStartId() {
        return startId;
    }

    public void setStartId(String startId) {
        this.startId = startId;
    }

    public String getEndId() {
        return endId;
    }

    public void setEndId(String endId) {
        this.endId = endId;
    }
}
