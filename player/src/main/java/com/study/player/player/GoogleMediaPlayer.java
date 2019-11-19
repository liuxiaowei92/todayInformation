package com.study.player.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.study.player.source.IPlayerSource;
import com.study.player.state.PlayerState;

/**
 * @authour lxw
 * @function IPlayer 和 创建的 MediaPlayer 相互结合
 * @date 2019/11/18
 */
public  class GoogleMediaPlayer implements IPlayer, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnInfoListener, MediaPlayer.OnVideoSizeChangedListener, MediaPlayer.OnBufferingUpdateListener {

    private MediaPlayer mMediaPlayer;
    private IPlayerListener mPlayerListener;

    public GoogleMediaPlayer(){
        mMediaPlayer=new MediaPlayer();
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnInfoListener(this);
        mMediaPlayer.setOnVideoSizeChangedListener(this);
        mMediaPlayer.setOnBufferingUpdateListener(this);
    }

    @Override
    public void release() {

    }

    @Override
    public void prepare(Context context, IPlayerSource iPlayerSource) {
        //操作mediaPlayer 去播放
        try {
            //MediaPlayer 播放系统资源时 只能用Uri方式
            mMediaPlayer.setDataSource(context, Uri.parse(iPlayerSource.getUrl()));
            mMediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void SetPlayingListener(IPlayerListener listener) {
        this.mPlayerListener=listener;
    }

    @Override
    public void paused() {
        if(mMediaPlayer!=null){
            mMediaPlayer.pause();
            setPlayerState(PlayerState.PAUSED);
        }
    }

    @Override
    public void reStart() {
        if(mMediaPlayer!=null){
            mMediaPlayer.start();
            setPlayerState(PlayerState.STARTED);
        }
    }

    //播放器准备成功后回调
    @Override
    public void onPrepared(MediaPlayer mp) {
        //初始化成功后 且播放器准备后 回调
        mp.start();
        setPlayerState(PlayerState.STARTED);
    }

    //更改状态
    private void setPlayerState(PlayerState state) {
        if(mPlayerListener!=null){
            mPlayerListener.playerStateChanged(state);
        }
    }

    //播放器如果遇到错误 会回到到java层
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.e("GoogleMediaPlayer","what="+what+"  extra"+extra);
        return false;
    }

    //播放完成后的监听
    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.stop();
        mp.release();
        setPlayerState(PlayerState.IDLE);
    }

    //播放卡顿的一些信息
    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }
    //横竖屏切换 视频大小改变回调
    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

    }
    //播放器的缓冲监听，用于一些UI展示，缓冲进度条
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }
}
