package com.study.refresh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @authour lxw
 * @function
 * @date 2019/10/29
 */
public class GodRefreshLayout extends LinearLayout {
    private BaseRefreshManager mRefreshManager;
    private Context mContext;
    private View mHeaderView;
    private int mHeaderViewHight;
    private int minHeadViewHeight; //头部布局最小高度
    private int maxHeadViewHeight;//头部布局最大高度
    private RefreshingListener mRefreshingListener; //正在刷新回调接口
    private RecyclerView mRecyclerView;
    private ScrollView mScrollView;

    public GodRefreshLayout(Context context) {
        super(context);
        initView(context);
    }


    public GodRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GodRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
    }

    /**
     * 开启下拉刷新，下拉刷新的效果，是默认的
     */
    public void setRefreshManager() {
        mRefreshManager = new DefaultRefreshManager(mContext);
        initHeaderview();
    }

    /**
     * 开启下拉刷新，使用用户自定义的下拉刷新效果
     */
    public void setRefreshManager(BaseRefreshManager manager) {
        mRefreshManager = manager;
        initHeaderview();
    }

    /**
     * 刷新完成后的操作
     */
    public void refreshOver() {
        hideHeadView(getHeadViewLayoutParams());
    }

    public interface RefreshingListener {
        void onRefreshing();
    }

    //自定义回调接口
    public void setRefreshListener(RefreshingListener refreshingListener) {
        this.mRefreshingListener = refreshingListener;
    }




    private void initHeaderview() {
        setOrientation(VERTICAL);
        mHeaderView = mRefreshManager.getHeaderView();
        mHeaderView.measure(0, 0);//测量后才能拿到高度
        mHeaderViewHight = mHeaderView.getMeasuredHeight();
        //最小高度
        minHeadViewHeight = -mHeaderViewHight;
        maxHeadViewHeight = (int) (mHeaderViewHight * 0.3f);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                mHeaderViewHight);
        //动态控制 显示隐藏
        params.topMargin = minHeadViewHeight; //隐藏起来
        addView(mHeaderView, 0, params);
    }

    private int downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) event.getY();
                if(downY==0){
                    downY=interceptDownY;
                }
                //当前Y轴的变化
                int dy = moveY - downY;
                if (dy > 0) {
                    //头部滑出
                    LayoutParams layoutParams = getHeadViewLayoutParams();
                    //阻尼效果dy/1.8f
                    int topMargin = (int) Math.min(dy / 1.8f + minHeadViewHeight, maxHeadViewHeight);
                    //这个时间的处理是为了 不断回调这个比例 用于一些视觉效果
                    if(topMargin<=0){
                        //0~1进行变化
                        float percent=((-minHeadViewHeight)-(-topMargin))*1.0f/(-minHeadViewHeight);
                        mRefreshManager.downRefreshScale(percent);
                    }else{
                        //随便给的值
                        mRefreshManager.downRefreshScale(1f);
                    }


                    if (topMargin < 0 && mCurrentRefreshState != RefreshState.DOWNREFRESH) {
                        mCurrentRefreshState = RefreshState.DOWNREFRESH;
                        //提示下拉刷新状态
                        handleRefreshState(mCurrentRefreshState);
                    } else if (topMargin >= 0 && mCurrentRefreshState != RefreshState.RELEASEREFRESH) {
                        mCurrentRefreshState = RefreshState.RELEASEREFRESH;
                        //提示释放刷新的一个状态
                        handleRefreshState(mCurrentRefreshState);
                    }
                    layoutParams.topMargin = topMargin;
                    mHeaderView.setLayoutParams(layoutParams);
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (handleEventUp(event)) {
                    return true;
                }
                break;
            default:
                break;

        }
        return super.onTouchEvent(event);
    }

    private int interceptDownY;
    private int interceptDownX;
    //事件分发，解决recyclerview适配
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                interceptDownY = (int) ev.getY();
                interceptDownX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                //1，确定滑动方向，只有上下滑动才会触发
                int dy = (int) (ev.getY() - interceptDownY);
                int dx = (int) (ev.getX() - interceptDownX);
                if (Math.abs(dy) > Math.abs(dx) && dy > 0 &&handleChildViewISTop()) {
                    //说明上下滑动
                    return true;//事件拦截
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    //判断子View 是否是滑动到顶端的
    private boolean handleChildViewISTop() {
        if(mRecyclerView!=null){
            return RefreshScrollingUtil.isRecyclerViewToTop(mRecyclerView);
        }
        if(mScrollView!=null){
            return RefreshScrollingUtil.isScrollViewOrWebViewToTop(mScrollView);
        }
        // TODO:
        return false;
    }

    //这个方法回调时 可以获取当前viewGroup子view
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View childAt = getChildAt(0);
        //获取recyclerview
        if(childAt instanceof RecyclerView){
            mRecyclerView= (RecyclerView) childAt;
        }else if(childAt instanceof ScrollView){
            mScrollView= (ScrollView) childAt;
        }

    }

    private boolean handleEventUp(MotionEvent event) {
        final LayoutParams layoutParams = getHeadViewLayoutParams();

        if (mCurrentRefreshState == RefreshState.DOWNREFRESH) {
            hideHeadView(layoutParams);
        } else if (mCurrentRefreshState == RefreshState.RELEASEREFRESH) {
            //保持一个刷新状态
            layoutParams.topMargin = 0;
            mHeaderView.setLayoutParams(layoutParams);
            mCurrentRefreshState = RefreshState.REFRESHING;
            handleRefreshState(mCurrentRefreshState);
            if (mRefreshingListener != null) {
                mRefreshingListener.onRefreshing();
            }
        }
        return layoutParams.topMargin > minHeadViewHeight;//大于证明滑动了
    }

    //隐藏头布局动画
    private void hideHeadView(final LayoutParams layoutParams) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(layoutParams.topMargin, minHeadViewHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                layoutParams.topMargin = animatedValue;
                mHeaderView.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentRefreshState = RefreshState.IDDLE;
                handleRefreshState(mCurrentRefreshState);
            }
        });
        valueAnimator.setDuration(300);
        valueAnimator.start();
    }

    //获取布局的LayoutParams
    private LayoutParams getHeadViewLayoutParams() {
        return (LayoutParams) mHeaderView.getLayoutParams();
    }

    private void handleRefreshState(RefreshState currentRefreshState) {
        switch (currentRefreshState) {
            case DOWNREFRESH:
                mRefreshManager.downRefresh();
                break;
            case RELEASEREFRESH:
                mRefreshManager.releaseRefresh();
                break;
            case IDDLE:
                mRefreshManager.iddleRefresh();
                break;
            case REFRESHING:
                mRefreshManager.refreshing();
                break;
            default:
                break;
        }
    }

    private RefreshState mCurrentRefreshState = RefreshState.IDDLE;


    /**
     * 定义下拉刷新的状态依次为 静止 下拉刷新 释放刷新 正在刷新
     */
    private enum RefreshState {
        IDDLE, DOWNREFRESH, RELEASEREFRESH, REFRESHING
    }
}
