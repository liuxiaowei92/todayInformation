package com.study.todayinformation;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.study.todayinformation.view.FullScreenVideoView;

import java.io.File;

import androidx.annotation.Nullable;

/**
 * @author Administrator
 * @authour lxw
 * @function
 * @date 2019/9/18
 */
public class SplashActivity extends BaseActivity {

    private FullScreenVideoView mVideoView;
    private TextView mTvTimer;
    private CustomCountDownTimer mTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mVideoView = (FullScreenVideoView) findViewById(R.id.vv_play);
        mTvTimer=(TextView) findViewById(R.id.tv_splash_timer);
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        //播放完成
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

        mTimer = new CustomCountDownTimer(5, new CustomCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                mTvTimer.setText(time+"秒");
            }

            @Override
            public void onfinish() {
                mTvTimer.setText("跳 过");
            }
        });
        mTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}
