package com.study.http;

import com.study.http.okhttp.OkHttpScheduler;
import com.study.http.request.IRequest;
import com.study.http.request.call.ICall;
import com.study.http.result.IResult;

import java.util.Map;

/**
 * @authour lxw
 * @function
 * @date 2019/9/25
 */
public class HttpHelper {

    private volatile static HttpSchedule httpSchedule;

    public static HttpSchedule getHttpScheduler() {
        if (httpSchedule == null) {
            synchronized ((HttpHelper.class)) {
                if (httpSchedule == null) {
                    httpSchedule = new OkHttpScheduler();
                }
            }
        }
        return httpSchedule;
    }

    // TODO: 2019/9/25
    protected static <T> IResult<T> execute(IRequest request, Map<String, Object> params) {

        request.setParams(params);
        ICall call = getHttpScheduler().newCall(request);
        return getHttpScheduler().execute(call);
    }
}
