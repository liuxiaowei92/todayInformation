package com.study.task;

/**
 * @authour lxw
 * @function
 * @date 2019/9/26
 */
public interface ITaskCallback<Result> {

    void onComplete(Result o);

    void onException(Throwable throwable);

}
