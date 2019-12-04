package com.study.ipc.request;

import com.study.ipc.CallBack;

import androidx.annotation.NonNull;

/**
 * @authour lxw
 * @function
 * @date 2019/11/21
 */
public class IpcRequest implements IRequest {
    private String mRequestKey;
    private long mTime;
    private String mParams;
    private CallBack mCallBack;

    public IpcRequest(){
        mTime=System.currentTimeMillis();
    }

    public IpcRequest(@NonNull String requestKey){
        this.mRequestKey=requestKey;
    }
    @Override
    public void setParams(@NonNull String param) {
        this.mParams=param;
    }

    @Override
    public String getParams() {
        return null;
    }

    @Override
    public String getRequestKey() {
        return mRequestKey;
    }

    @Override
    public void addCallBack(CallBack callBack) {
        this.mCallBack=callBack;
    }

    @Override
    public CallBack getCallBack() {
        return mCallBack;
    }

    @Override
    public long getAddTime() {
        return mTime;
    }


    @Override
    public int compareTo(IRequest o) {
        return (int) (this.getAddTime()-o.getAddTime());
    }
}
