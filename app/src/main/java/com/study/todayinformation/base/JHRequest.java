package com.study.todayinformation.base;

import com.study.http.parser.DefaultResultParser;
import com.study.http.request.IRequest;
import com.study.http.annotation.RequestMethod;
import com.study.http.request.LfRequest;

import java.lang.reflect.Type;

/**
 * @authour lxw
 * @function
 * @date 2019/9/25
 */
public class JHRequest extends LfRequest {
    public static IRequest sendHttp(String path, @RequestMethod int requestMethod, Type type){

        JHRequest request=new JHRequest();
        request.host= IHostManager.jhHost;
        request.path=path;
        request.requestMethod=requestMethod;
        request.type=type;
        request.resultParser= DefaultResultParser.getInstance();
        return request;

    }
}
