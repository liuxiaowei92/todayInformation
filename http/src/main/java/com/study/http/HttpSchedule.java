package com.study.http;

import com.study.http.parser.IParser;
import com.study.http.request.IRequest;
import com.study.http.request.call.ICall;
import com.study.http.response.IResponse;
import com.study.http.result.IResult;

/**
 * @authour lxw
 * @function
 * @date 2019/9/25
 */
public abstract class HttpSchedule {

    public abstract ICall newCall(IRequest request);

    public IResult execute(ICall call) {
        //IResponse 和 IResult进行转换
        IResponse iResponse = call.execute();
        IRequest request = call.getRequest();
        IParser parser = request.getParser();
        return parser.parseResponse(request,iResponse);
    }
}
