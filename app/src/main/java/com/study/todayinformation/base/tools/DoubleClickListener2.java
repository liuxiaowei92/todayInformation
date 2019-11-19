package com.study.todayinformation.base.tools;

import android.view.View;

/**
 * @authour lxw
 * @function 防止按钮连续点击
 * @date 2019/11/15
 */
public abstract class DoubleClickListener2 implements View.OnClickListener {
    private final int MIT_TIME = 1000;
    private long old;

    protected abstract void onNoDoubleClick(View v);

    @Override
    public void onClick(View v) {
        long now = System.currentTimeMillis();
        if (now - old > MIT_TIME) {
            onNoDoubleClick(v);
        }
    }
}
