package com.study.ipc.result;

/**
 * @authour lxw
 * @function
 * @date 2019/11/21
 */
public interface IResult {

    boolean isSuccess();

    int getCode();

    String data();
}
