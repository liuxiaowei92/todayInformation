package com.study.http.request.call;

import com.study.http.request.IRequest;
import com.study.http.response.IResponse;

/**
 * @authour lxw
 * @function
 * @date 2019/9/25
 */
public interface ICall {
    IResponse execute();

    IRequest getRequest();
}
