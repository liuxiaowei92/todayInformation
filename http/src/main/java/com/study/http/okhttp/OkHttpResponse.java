package com.study.http.okhttp;

import com.study.http.response.IResponse;

import java.io.IOException;

import okhttp3.Response;

/**
 * @authour lxw
 * @function
 * @date 2019/9/26
 */
public class OkHttpResponse implements IResponse {

    private final Response response;

    public OkHttpResponse(Response response) {
        this.response=response;
    }

    @Override
    public String getBodyStr() {
        try {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
