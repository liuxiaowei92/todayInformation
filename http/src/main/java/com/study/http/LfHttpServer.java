package com.study.http;

import com.study.http.request.IRequest;
import com.study.http.result.IResult;

import java.util.Map;

/**
 * @authour lxw
 * @function
 * @date 2019/9/25
 */
public class LfHttpServer {

    //泛型的另一种写法
    protected <T>IResult<T> execute(IRequest request, Map<String,Object> params){
        return HttpHelper.execute(request,params);
    }
}
