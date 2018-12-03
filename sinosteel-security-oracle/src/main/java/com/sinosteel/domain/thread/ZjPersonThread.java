package com.sinosteel.domain.thread;

import com.alibaba.fastjson.JSONObject;
import com.sinosteel.service.ZjPersonService;
import org.apache.log4j.Logger;

import java.util.concurrent.Callable;


/**
 * @Description:
 * @Company: CVICSE BigData
 * @Auther: peng_cqing
 * @Created: 2018/11/26 11:58
 */
public class ZjPersonThread implements Callable<JSONObject> {
    //日志
    private static Logger logger = Logger.getLogger(ZjPersonThread.class);
    private Thread t;
    private JSONObject params; // 数据参数
    private JSONObject result; // 数据结果
    private ZjPersonService zjPersonService;


    public ZjPersonThread(JSONObject params,ZjPersonService zjPersonService) {
        this.params = params;
        this.zjPersonService=zjPersonService;
    }

    @Override
    public JSONObject call() throws Exception {
        result=zjPersonService.queryZjPersons(params);
        setResult(result);
        return result;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }
}
