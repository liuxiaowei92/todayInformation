package com.study.todayinformation.base;

import android.app.Application;

import com.study.todayinformation.base.crash.CrashProtectManager;

/**
 * @authour lxw
 * @function
 * @date 2019/11/14
 */
public class TodayInformationApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashProtectManager.getInstance(this).init();
    }
}
