package com.study.todayinformation.main.beijing;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.study.todayinformation.R;

import androidx.annotation.NonNull;

/**
 * @authour lxw
 * @function 服务获取数据 进程间通信
 * @date 2019/11/20
 */
public class MainProcessService extends Service {
    public static final int SHANGHAI_DETAIL=0;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SHANGHAI_DETAIL:
                    Messenger replyTo = msg.replyTo;
                    Message message=new Message();
                    Bundle bundle=new Bundle();
                    bundle.putString("process",ProcessDataTest.getInstance().getProcessDec());
                    message.setData(bundle);
                    try {
                        replyTo.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                    default:
                        break;
            }
        }
    };

    //service 跨进程通信的原理就是这个 Messenger
    private Messenger mMessenger=new Messenger(mHandler);
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel("mainProcess", "test", NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(notificationChannel);
            Notification notification = new Notification.Builder(this,"mainProcess")
                    .setContentTitle("标题")
                    .setContentText("内容")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .build();
            //前台服务  通知栏
            startForeground(121,notification);
        }


    }
}
