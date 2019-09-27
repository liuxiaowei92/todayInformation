package com.study.http.request;

import com.study.http.parser.IParser;
import com.study.http.request.host.IHost;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @authour lxw
 * @function
 * @date 2019/9/25
 */
public interface IRequest {

    void setParams(Map<String, Object> params);

    Map<String,Object> getParams();

    int getRequestMethod();

    IHost getHost();

    String getPath();

    IParser getParser();

    Type getType();
}
