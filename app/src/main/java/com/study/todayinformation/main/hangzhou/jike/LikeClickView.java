package com.study.todayinformation.main.hangzhou.jike;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * @authour lxw
 * @function 自定义view 仿即刻点赞
 * @date 2019/10/11
 */
public class LikeClickView extends View {
    public LikeClickView(Context context) {
        this(context,null,0);
        init();
    }

    public LikeClickView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        init();
    }

    public LikeClickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }

}
