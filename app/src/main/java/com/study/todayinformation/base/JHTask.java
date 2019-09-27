package com.study.todayinformation.base;

import com.study.http.result.IResult;
import com.study.http.result.IResultCallBack;
import com.study.http.result.Result;
import com.study.task.LfTask;

/**
 * @authour lxw
 * @function http 和 task 库 结合
 * @date 2019/9/27
 */
public abstract class JHTask<T> extends LfTask<IResult<T>> implements IResultCallBack<T> {

    @Override
    public void onComplete(IResult<T> iResult) {
        if(iResult!=null){
            if(iResult.isSuccess()){
                onSuccess(iResult);
            }else{
                onFailed(Result.failed(Result.CODE_404)); //1次失败
            }
        }else{
            onFailed(Result.failed(Result.CODE_504));//2次失败
        }

    }

    @Override
    public void onFailed(IResult t) {
        switch (t.getCode()){
            //可以做成统一错误码处理
            case Result.CODE_404:
                break;
            case Result.CODE_505:
                break;
                default:
        }
    }

    @Override
    public void onException(Throwable throwable) {
        onFailed(Result.failed(Result.CODE_504));//3次失败
    }
}
