package com.study.http.request;

import com.study.http.annotation.RequestMethod;
import com.study.http.parser.IParser;
import com.study.http.request.host.IHost;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @authour lxw
 * @function
 * @date 2019/9/25
 */
public class LfRequest implements IRequest {
    protected IHost host;

    protected Map<String, Object> params;

    protected Type type;

    protected IParser resultParser;

    @RequestMethod
    protected int requestMethod;

    protected String path;


    @Override
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }

    @Override
    public int getRequestMethod() {
        return requestMethod;
    }

    @Override
    public IHost getHost() {
        return host;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public IParser getParser() {
        return resultParser;
    }

    @Override
    public Type getType() {
        return type;
    }
}
