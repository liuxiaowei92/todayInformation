package com.study.todayinformation.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.study.todayinformation.base.BaseActivity;
import com.study.todayinformation.main.MainActivity;
import com.study.todayinformation.R;
import com.study.todayinformation.base.ViewInject;

import java.io.File;

import butterknife.BindView;

/**
 * @author Administrator
 * @authour lxw
 * @function
 * @date 2019/9/18
 */
@ViewInject(mainLayoutId = R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements ISplashActivityContract.Iview {

    @BindView(R.id.vv_play)
    FullScreenVideoView mVideoView;
    @BindView(R.id.tv_splash_timer)
    TextView mTvTimer;

    private ISplashActivityContract.IPresenter mTimerPresenter;

    @Override
    public void afterBindView() {
        initListener();
        initVideo();
        initTimerPresenter();
    }

    private void initTimerPresenter() {
        mTimerPresenter = new SplashTimerPresenter(this);
        mTimerPresenter.initTimer();
    }


    private void initVideo() {
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    private void initListener() {
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //播放完成
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

    }

    @Override
    public void setTvTimer(String timer) {
        mTvTimer.setText(timer);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mTimerPresenter.onDestroy();
//    }



}
