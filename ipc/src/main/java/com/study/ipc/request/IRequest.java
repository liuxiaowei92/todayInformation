package com.study.ipc.request;

import com.study.ipc.CallBack;

/**
 * @authour lxw
 * @function
 * @date 2019/11/21
 */
public interface IRequest extends Comparable<IRequest> {

    void setParams(String param);

    String getParams();

    String getRequestKey();

    void addCallBack(CallBack callBack);

    CallBack getCallBack();

    long getAddTime();
}
