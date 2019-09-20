package com.study.todayinformation.splash;


import android.os.Handler;

/**
 * @authour lxw
 * @function 计时器
 * @date 2019/9/18
 */
public class CustomCountDownTimer implements Runnable {

    private int time;
    private final ICountDownHandler countDownHandler;
    private final Handler mHandler;
    private boolean isRun;
    private int countDownTime;
    //1，创建构造函数  传递总秒数  创建观察者模式回调
    public CustomCountDownTimer(int time, ICountDownHandler countDownHandler) {
        mHandler = new Handler();
        this.countDownTime=time;
        this.time = time;
        this.countDownHandler = countDownHandler;
    }

    //具体逻辑
    @Override
    public void run() {
        if(isRun){
            if(countDownHandler!=null){
                countDownHandler.onTicker(countDownTime);
            }
            if(countDownTime==0){
                cancel();
                if(countDownHandler!=null){
                    countDownHandler.onfinish();
                }
            }else{
                countDownTime=time--;
                mHandler.postDelayed(this,1000);
            }
        }
    }

    //开始倒计时
    public void start(){
        isRun=true;
        mHandler.post(this);
    }
    //调出循环 终止
    public void cancel(){
        isRun=false;
        mHandler.removeCallbacks(this);
    }

    //观察者回调接口（ioc数据回调
    public interface ICountDownHandler {
        //倒计时回调
        void onTicker(int time);

        void onfinish();
    }

}
