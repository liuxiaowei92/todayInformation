package com.study.todayinformation.main.shanghai.module;

import com.study.http.LfHttpServer;
import com.study.http.result.IResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @authour lxw
 * @function 负责http请求
 * @date 2019/9/25
 */
public class ShanghaiDetailHttpTask<T> extends LfHttpServer {

    public IResult<T> getXiaoHuaList(String sort, String page, String pageSize){
        Map<String,Object> params=new HashMap<>();
        params.put("sort",sort);
        params.put("page",page);
        params.put("pagesize",pageSize);
        params.put("time",System.currentTimeMillis()/1000);
        params.put("key","32308249f399340c734fd0106d2a43e0");
        return super.execute(ShanghaiDetailRequest.xiaoHuaRequest,params);
    }

}
