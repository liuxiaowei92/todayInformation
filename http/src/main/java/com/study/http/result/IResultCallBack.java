package com.study.http.result;

/**
 * @authour lxw
 * @function
 * @date 2019/9/27
 */
public interface IResultCallBack<T> {

    void onSuccess(IResult<T> t);

    void onFailed(IResult t);
}
