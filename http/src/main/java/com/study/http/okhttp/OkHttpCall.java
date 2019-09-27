package com.study.http.okhttp;

import com.study.http.request.IRequest;
import com.study.http.request.call.ICall;
import com.study.http.response.IResponse;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @authour lxw
 * @function 静态代理设计模式
 * @date 2019/9/25
 */
public class OkHttpCall implements ICall {


    private final Call call;
    private IRequest request;

    public OkHttpCall(IRequest request, Call call) {
        this.call=call;
        this.request=request;
    }

    @Override
    public IResponse execute() {
        Response response=null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OkHttpResponse okHttpResponse= new OkHttpResponse(response);
        return okHttpResponse;
    }

    @Override
    public IRequest getRequest() {
        return request;
    }
}
