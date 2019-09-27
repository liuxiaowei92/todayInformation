package com.study.http.parser;

import com.study.http.request.IRequest;
import com.study.http.response.IResponse;
import com.study.http.result.IResult;

/**
 * @authour lxw
 * @function
 * @date 2019/9/27
 */
public interface IParser {
    IResult parseResponse(IRequest iRequest, IResponse iResponse);
}
