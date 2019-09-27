package com.study.http.result;

/**
 * @authour lxw
 * @function 所有IResponse 解析后的结果
 * @date 2019/9/26
 */
public interface IResult<T> {
    boolean isSuccess();

    int getCode();

    T data();
}
