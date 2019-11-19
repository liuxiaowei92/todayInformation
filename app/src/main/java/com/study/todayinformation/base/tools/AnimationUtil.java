package com.study.todayinformation.base.tools;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * @authour lxw
 * @function
 * @date 2019/11/15
 */
public class AnimationUtil {
    /**
     * X轴方向的属性动画
     * @param taget
     * @param positionStart
     * @param positionEnd
     * @param listener
     */
    public static void startTranslationXAnim(View taget, float positionStart, float positionEnd, Animator.AnimatorListener listener){
        ObjectAnimator titleAnim=ObjectAnimator.ofFloat(taget,"translationX",positionStart,positionEnd);
        titleAnim.setDuration(1000);
        titleAnim.start();
        if(listener!=null) {
            titleAnim.addListener(listener);
        }
    }
}
