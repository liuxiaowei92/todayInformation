package com.study.todayinformation.main.shanghai.presenter;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.study.player.PlayerService;
import com.study.player.source.RawPlayerSourse;
import com.study.todayinformation.R;
import com.study.todayinformation.base.BasePresenter;
import com.study.todayinformation.base.helper.ContextHelper;
import com.study.todayinformation.main.shanghai.IPlayerServiceContract;

/**
 * @authour lxw
 * @function
 * @date 2019/9/26
 */
public class PlayerServicePresenter extends BasePresenter<IPlayerServiceContract.IView> implements IPlayerServiceContract.IPresenter {

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //IOC数据回调  和Service链接成功后调用
            PlayerService.PlayerBind binder = (PlayerService.PlayerBind) service;
            playerService = binder.getService();
            playOrPaused();


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //和Service断开后调用
            if (playerService != null) {
                playerService.unbindService(mConnection);
                playerService = null;
            }
        }
    };
    private PlayerService playerService;

    public PlayerServicePresenter(IPlayerServiceContract.IView view) {
        super(view);
    }

    @Override
    public void bindService(Context context) {
        if (playerService != null) {
            playOrPaused();
        } else {
            Intent intent = new Intent(context, PlayerService.class);
            //服务启动两种方式：startService 随着进程的销毁而销毁 不能和页面进行交互
            //context.startService()
            //bindService 随着activity的销毁而销毁 可以多次绑定 只要有一个就不会销毁
            context.bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
        }
    }

    @Override
    public void playOrPaused() {
        if (playerService != null) {
            //开启播放音乐
            playerService.playOrPause(new RawPlayerSourse().setPath(ContextHelper.getInstance().getApplicationContext().getPackageName(),
                    R.raw.minyao), ContextHelper.getInstance().getApplicationContext());
        }
    }
}
