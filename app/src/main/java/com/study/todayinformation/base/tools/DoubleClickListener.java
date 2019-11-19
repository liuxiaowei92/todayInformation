package com.study.todayinformation.base.tools;

import android.view.View;

/**
 * @authour lxw
 * @function 防止按钮连续点击
 * @date 2019/11/15
 */
public class DoubleClickListener implements View.OnClickListener {
    private final View.OnClickListener mOnClickListener;
    private long old;
    //两次连续点击间隔不能超过1000毫秒
    private final int MIN_CLICK_DELAY_TIME = 1000;

    //方法一
    public DoubleClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    //方法二：抽象方法
//    protected abstract void onNoDoubleClick(View v);

    @Override
    public void onClick(View v) {
        //通过两次点击时间对比来判断是否连续
        long now = System.currentTimeMillis();
        if (now - old > MIN_CLICK_DELAY_TIME) {
            //再将事件回调出去
            if (mOnClickListener != null) {
                mOnClickListener.onClick(v);
            }
            //方法二：抽象方法
//            onNoDoubleClick(v);
        }
        old = now;
    }
}
