package com.study.todayinformation.base;

import android.app.Application;

import com.study.todayinformation.base.helper.ContextHelper;

/**
 * @authour lxw
 * @function  业务尽量不要写在这里
 * @date 2019/11/14
 */
public class TodayInformationApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化异常保护管理类
//        CrashProtectManager.getInstance(this).init();

        //全局context获取类 初始化全局context
        ContextHelper.getInstance().init(this);
    }
}
