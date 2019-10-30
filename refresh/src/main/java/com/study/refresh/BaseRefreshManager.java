package com.study.refresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @authour lxw
 * @function
 * @date 2019/10/29
 */
public abstract class BaseRefreshManager {

    public LayoutInflater mlayoutInflater;

    public BaseRefreshManager(Context context){
        mlayoutInflater = LayoutInflater.from(context);
    }

    public abstract View getHeaderView();

    public abstract void downRefresh();

    public abstract void releaseRefresh();

    public abstract void iddleRefresh();

    public abstract void refreshing();

    public abstract void downRefreshScale(float precent);
}
