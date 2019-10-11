package com.study.todayinformation.main.hangzhou.design;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

/**
 * @authour lxw
 * @function 自定义Behavior
 * @date 2019/10/11
 */
public class BottomShowBehavior extends CoordinatorLayout.Behavior<TextView> {

    public BottomShowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     *  这个方法的回调时机：即将发生嵌套滚动时
     */
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes== ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    //发生嵌套滚动的时候 回调
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        //说明向下滑动
        if(dyConsumed+dyUnconsumed >0){
            //隐藏child
            if(child.getVisibility()==View.VISIBLE) {
                BottomAnim.hide(child); //隐藏不能使用gong
            }
        }else{ //向上滑动
            //展示child
            if(child.getVisibility()!=View.VISIBLE) {
                BottomAnim.show(child);
            }
        }
    }

}
