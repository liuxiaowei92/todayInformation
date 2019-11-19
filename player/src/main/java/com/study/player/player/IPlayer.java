package com.study.player.player;

import android.content.Context;

import com.study.player.source.IPlayerSource;

/**
 * @authour lxw
 * @function
 * @date 2019/11/15
 */
public interface IPlayer {
    /**
     * 播放器释放
     */
    void release();

    //准备播放
    void prepare(Context context, IPlayerSource playerSource);

    //设置IOC回调 用来改变播放状态
    void SetPlayingListener(IPlayerListener listener);

    //暂停
    void paused();

    //继续播放
    void reStart();
}
