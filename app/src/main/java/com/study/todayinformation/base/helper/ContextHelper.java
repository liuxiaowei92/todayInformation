package com.study.todayinformation.base.helper;

import android.app.Application;
import android.content.Context;

/**
 * @authour lxw
 * @function 获取全局context
 * @date 2019/11/15
 */
public class ContextHelper {
    private static ContextHelper mInstance;
    private Application mApplication;

    public static ContextHelper getInstance(){
        if(mInstance==null){
            mInstance=new ContextHelper();
        }
        return mInstance;
    }
    public void init(Application application){
        this.mApplication=application;
    }
    public Context getApplicationContext(){
        return this.mApplication.getApplicationContext();
    }
}
