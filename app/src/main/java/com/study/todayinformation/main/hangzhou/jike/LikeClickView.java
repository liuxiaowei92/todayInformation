package com.study.todayinformation.main.hangzhou.jike;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.study.todayinformation.R;
import com.study.todayinformation.main.tools.SystemUtil;

/**
 * @authour lxw
 * @function 自定义view 仿即刻点赞
 * @date 2019/10/11
 */
public class LikeClickView extends View {

    private boolean isLike;
    private Bitmap likeBitmap;
    private Bitmap shiningBitmap;
    private Bitmap unLikeBitmap;
    private Paint bitmapPaint;
    private int left;
    private int top;
    private float handScale=1.0f;
    private int centerY;
    private int centerX;

    public LikeClickView(Context context) {
        this(context, null, 0);
        init();
    }

    public LikeClickView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public LikeClickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性的值，固定写法
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.JiKeLikeView);
        isLike = typedArray.getBoolean(R.styleable.JiKeLikeView_is_like, false);
        typedArray.recycle();
        init();
    }

    private void init() {

        Resources resources = getResources();
        likeBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_like);
        unLikeBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_unlike);
        shiningBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_like_shining);
        bitmapPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //解决用户传入的大小 和 onDraw大小冲突
        int measureWidth = 0;
        int measureHeight = 0;
        //1,定义自定义控件的最大值 和用户相比 也可以直接
        int maxHeight = unLikeBitmap.getHeight() + SystemUtil.dp2px(getContext(), 20);
        int maxWidth = unLikeBitmap.getWidth() + SystemUtil.dp2px(getContext(), 30);

        //拿到当前控件的测量模式
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        //拿到用户传过来的大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heigthSize = MeasureSpec.getSize(heightMeasureSpec);
        if (mode != MeasureSpec.EXACTLY) {
            //EXACTLY 精确模式 当我们将layout_width or layout_height 设置为具体数值的时候，如100dp match_parent的时候调用精确模式
            //AT_MOST 最大值模式 当我们将layout_width or layout_height 设置为wrap_content的时候 系统调用该模式。

            //测量模式未指定 如果有背景 背景多大 我们这个控件就有多大 获取当前控件背景大小
            int suggestedMinimumWidth = getSuggestedMinimumWidth();
            int suggestedMinimumHeight = getSuggestedMinimumHeight();
            if (suggestedMinimumWidth == 0) {
                //说明没有背景
                measureWidth = maxWidth;
            } else {
                measureWidth = Math.min(suggestedMinimumWidth, maxWidth);
            }
            if (suggestedMinimumHeight == 0) {
                //说明没有背景
                measureHeight = maxHeight;
            } else {
                measureHeight = Math.min(suggestedMinimumHeight, maxHeight);
            }

        } else {
            //测量模式指定  根据用户定义的大小指定
            measureWidth = Math.min(maxWidth, widthSize);
            measureHeight = Math.min(maxHeight, heigthSize);
        }
        setMeasuredDimension(measureWidth, measureHeight);

        getOnDrawPading(measureWidth, measureHeight);
    }

    public void getOnDrawPading(int measureWidth, int measureHeight) {
        //图片padding
        int bitmapWidth = likeBitmap.getWidth();
        int bitmapHeight = likeBitmap.getHeight();
        left = (measureWidth - bitmapWidth) / 2;
        top = (measureHeight - bitmapHeight) / 2;

        //动画中心点
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        centerX = width/2;
        centerY = height/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //重复绘制 尽量少new对象 减少计算
        super.onDraw(canvas);
        Bitmap handBitmap = isLike ? likeBitmap : unLikeBitmap;
        //属性动画 使用canvas scale 及其他效果方法时 必须先用save 再用restore（这两个方法成对出现）
        canvas.save();
        //centerx 中心点
        canvas.scale(handScale,handScale,centerX,centerY);
        //画图
        canvas.drawBitmap(handBitmap, left, top, bitmapPaint);
        canvas.restore();

        if(isLike){
            canvas.drawBitmap(shiningBitmap,left+10,0,bitmapPaint);
        }
    }

    //当这个自定义view 从界面脱离消失时调用
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        likeBitmap.recycle();
        unLikeBitmap.recycle();
        shiningBitmap.recycle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                onClick();//点击事件
                break;
                default:break;
        }
        return super.onTouchEvent(event);
    }

    private void onClick() {
        isLike=!isLike;
        //%% 第一种 属性动画 作用于所有类
//        ObjectAnimator handScale = ObjectAnimator.ofFloat(this, "handScale", 1.0f, 0.8f, 1.0f);
//        handScale.setDuration(250);
//        handScale.start();

        //第二种：
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f, 0.8f, 1.0f);
        valueAnimator.setDuration(250);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                handScale=animatedValue;
                invalidate();
            }
        });

        //主要方法 只要执行就会重新调用 onDraw
//        invalidate();
    }
    //%%使用objectAnimator 系统会自动调用该属性的 Set方法  必须写  handScale 不报错
    public void setHandScale(float value){
        this.handScale=value;
        invalidate();
    }
}
