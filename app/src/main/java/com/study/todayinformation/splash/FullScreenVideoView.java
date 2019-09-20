package com.study.todayinformation.splash;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * @authour lxw
 * @function
 * @date 2019/9/18
 */
public class FullScreenVideoView extends VideoView {

    //主要用于直接new出来的对象
    public FullScreenVideoView(Context context) {
        super(context);
    }

    //主要用于xml文件中,支持自定义属性
    public FullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //主要用于xml文件中，支持自定义属性，支持style样式
    public FullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //widthMeasurespec 包含两个主要内容 1，测量模式 2，测量大小
        int width=getDefaultSize(0,widthMeasureSpec);
        int height=getDefaultSize(0,heightMeasureSpec);
        setMeasuredDimension(width,height);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
