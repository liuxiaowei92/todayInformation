package com.study.todayinformation.main.hangzhou.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.study.refresh.BaseRefreshManager;
import com.study.todayinformation.R;

/**
 * @authour lxw
 * @function 自定义美团下拉刷新
 * @date 2019/10/30
 */
public class MeiTuanRefreshManager extends BaseRefreshManager {

    private ImageView mImageView;

    public MeiTuanRefreshManager(Context context) {
        super(context);
    }

    @Override
    public View getHeaderView() {
        View view = mlayoutInflater.inflate(R.layout.meituan_header_refresh_layout, null, false);
        mImageView=view.findViewById(R.id.loading);
        return view;
    }

    @Override
    public void downRefresh() {
        Log.i("MeiTuanRefreshManager","downRefresh");
    }
    //释放刷新 变成美团小人
    @Override
    public void releaseRefresh() {
        mImageView.setImageResource(R.drawable.meituan_loading_pre);
        AnimationDrawable animationDrawable= (AnimationDrawable) mImageView.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void iddleRefresh() {
        mImageView.clearAnimation();
        mImageView.setImageResource(R.mipmap.pull_image);
        mImageView.setScaleX(0);
        mImageView.setScaleY(0);
    }

    //正在刷新
    @Override
    public void refreshing() {
        mImageView.setImageResource(R.drawable.meituan_loading);
        AnimationDrawable animationDrawable= (AnimationDrawable) mImageView.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void downRefreshScale(float precent) {
        mImageView.setScaleX(precent);
        mImageView.setScaleY(precent);
    }
}
