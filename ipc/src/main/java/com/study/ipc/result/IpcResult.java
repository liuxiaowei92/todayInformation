package com.study.ipc.result;

/**
 * @authour lxw
 * @function
 * @date 2019/11/21
 */
public class IpcResult implements IResult{

    public String data;
    public int code;
    public boolean success;

    /**
     * 失败
     * @return
     */
    public static IResult getErrorResult(){
        IpcResult result=new IpcResult();
        result.success=false;
        return result;
    }

    /**
     * 成功
     * @return
     */
    public static IResult getSuccessResult(String data){
        IpcResult result=new IpcResult();
        result.success=true;
        result.data=data;
        return result;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String data() {
        return data;
    }
}
