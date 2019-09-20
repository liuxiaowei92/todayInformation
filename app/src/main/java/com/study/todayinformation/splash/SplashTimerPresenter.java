package com.study.todayinformation.splash;

import com.study.todayinformation.mvp.base.BaseMvpPresenter;

/**
 * @authour lxw
 * @function p层 用来做定时器逻辑
 * @date 2019/9/19
 */
public class SplashTimerPresenter extends BaseMvpPresenter<ISplashActivityContract.Iview> implements ISplashActivityContract.IPresenter {

    private CustomCountDownTimer mTimer;

    public SplashTimerPresenter(ISplashActivityContract.Iview view) {
        super(view);
    }


    @Override
    public void initTimer() {
        mTimer = new CustomCountDownTimer(5, new CustomCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                getView().setTvTimer(time + "秒");
            }

            @Override
            public void onfinish() {
                getView().setTvTimer("跳 过");
            }
        });
        mTimer.start();
    }

    public void cancel() {
        mTimer.cancel();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        cancel();
    }

    /**
     * 防止 空指针
     * @return
     */
    @Override
    protected ISplashActivityContract.Iview getEmptyView() {
        return ISplashActivityContract.emptyView;
    }
}
