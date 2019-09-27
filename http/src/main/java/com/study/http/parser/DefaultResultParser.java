package com.study.http.parser;

import com.google.gson.Gson;
import com.study.http.request.IRequest;
import com.study.http.response.IResponse;
import com.study.http.result.IResult;
import com.study.http.result.Result;

import java.lang.reflect.Type;

/**
 * @authour lxw
 * @function
 * @date 2019/9/27
 */
public class DefaultResultParser implements IParser {

    private static DefaultResultParser mInstance;
    private final Gson mGson;

    private DefaultResultParser(){
        mGson = new Gson();
    }

    public static IParser getInstance() {
        if (mInstance == null) {
            mInstance = new DefaultResultParser();
        }
        return mInstance;
    }

    @Override
    public IResult parseResponse(IRequest request, IResponse response) {
        Type type = request.getType();
        String bodyStr = response.getBodyStr();
        Object object = mGson.fromJson(bodyStr, type);
        if(object==null){
            return Result.failed(Result.CODE_404);
        }else{
            return Result.success(object);
        }
    }
}
