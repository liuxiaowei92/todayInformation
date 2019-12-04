package com.study.todayinformation.main.shanghai.view;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.ipc.CallBack;
import com.study.ipc.IpcManager;
import com.study.ipc.request.IpcRequest;
import com.study.ipc.result.IResult;
import com.study.todayinformation.R;
import com.study.todayinformation.base.BaseActivity;
import com.study.todayinformation.base.ViewInject;
import com.study.todayinformation.main.beijing.MainProcessService;
import com.study.todayinformation.main.shanghai.IShanghaiDetailContract;
import com.study.todayinformation.main.shanghai.dto.ShanghaiDetailBean;
import com.study.todayinformation.main.shanghai.presenter.ShanghaiDetailPresenter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * @authour lxw
 * @function android5.0 以上转场动画
 * @date 2019/9/24
 */
@ViewInject(mainLayoutId = R.layout.activity_shanghai_detail)
public class ShanghaiDetailActivity extends BaseActivity implements IShanghaiDetailContract.IView {

    IShanghaiDetailContract.IPresenter mPresenter=new ShanghaiDetailPresenter(this);
    public static String mActivityOptionsCompt = "ShanghaiDetailActivity";
    @BindView(R.id.iv_shanghai_detail)
    ImageView ivShanghaiDetail;
    @BindView(R.id.tv_crash)
    TextView mTvCrash;
    private GetProcessReceiver mGetProcessReceiver;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle data = msg.getData();
            Log.e(mActivityOptionsCompt,"process1="+data.get("process"));
        }
    };
    private Messenger mMessengerClient=new Messenger(mHandler);

    private ServiceConnection mConnection=new ServiceConnection() {
        private Messenger mMessenger;

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
            Message message=new Message();
            message.what=MainProcessService.SHANGHAI_DETAIL;
            //双向通信
            message.replyTo=mMessengerClient;
            try {
                mMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void afterBindView() {
        initAnima();
        //广播方式 获取数据 进程间通信
//        initReceiver();
//        initProcessData();
        //内容提供者 获取数据
//        initProviderData();
        //服务 获取数据
//        initProcessService();
         initIpc();
//        initGetNetData();
//        initPostNetData();

        //手动crash
//        ivShanghaiDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //主线程崩溃
//                String s=null;
//                s.toString();
//            }
//        });
//        mTvCrash.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //子线程崩溃
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String s=null;
//                        s.toString();
//                    }
//                }).start();
//            }
//        });
    }

    private void initIpc() {
        IpcRequest request=new IpcRequest("shanghai_detail");
        IpcManager.getInstance(this).excuteAsyn(request, new CallBack() {
            @Override
            public void callBack(IResult result) {
                String data = result.data();
                Log.e("数据请求",data);
            }
        });
    }

    private void initProcessService() {
        Intent intent=new Intent(this, MainProcessService.class);
        bindService(intent,mConnection, Service.BIND_AUTO_CREATE);
    }

    private void initProviderData() {
        Uri insert = getContentResolver().insert(Uri.parse("content://com.study.todayinformation.process.data")
                , new ContentValues());
        Log.e(mActivityOptionsCompt,"process="+insert.toString());

    }

    private void initReceiver() {
        mGetProcessReceiver = new GetProcessReceiver();
        registerReceiver(mGetProcessReceiver,new IntentFilter("beijing_post_process_data"));
    }

    private void initProcessData() {
        //发送广播
        Intent intent=new Intent("shanghai_get_process_data");
        sendBroadcast(intent);
    }

    private class GetProcessReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String processDec = intent.getStringExtra("processDec");
            Log.e("ShanghaiDetailActivity","processDec="+processDec);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //广播方式
//        unregisterReceiver(mGetProcessReceiver);
    }

    //okttp请求网络
    private void initGetNetData() {

        mPresenter.getNetData(2);

//        GetXiaoHuaTask task=new GetXiaoHuaTask();
//        task.execute("desc","1","2");

//        //1，可以隔离
//        OkHttpClient client = new OkHttpClient();
//        //2，传递参数  构建请求  1）url  2）参数
//        HttpUrl.Builder builder = HttpUrl.parse("http://v.juhe.cn/joke/content/list.php").newBuilder();
//        builder.addQueryParameter("key","value");
//        builder.addQueryParameter("key","value");
//        builder.addQueryParameter("key","value");
//        //3，构建request
//        Request request = new Request.Builder()
//                .url(builder.build())
//                .get()
//                .build();
//        //4，构建call
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                Log.i("initGetNetData", "onFailure:" + e.toString());
//            }
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                Log.i("initGetNetData", "onResponse=" + response.body().string());
//            }
//        });
    }

    private void initPostNetData() {

        OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBody= new FormBody.Builder();
        formBody.add("key","value");
        Request request = new Request.Builder()
                .url("")
                .post(formBody.build())
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("initGetNetData", "onFailure:" + e.toString());
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.i("initGetNetData", "onResponse=" + response.body().string());
            }
        });
    }

    private void initAnima() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //布局文件配置后 可以去掉
            ViewCompat.setTransitionName(ivShanghaiDetail,mActivityOptionsCompt);
            //开启转场动画
            startPostponedEnterTransition();
        }
    }

    /**
     * 5.0系统的转场动画：共享元素动画
     */
    public static void start_5_0(Activity activity, View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(activity, ShanghaiDetailActivity.class);

            Pair pair = new Pair(view, mActivityOptionsCompt);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair);
            ActivityCompat.startActivity(activity, intent, optionsCompat.toBundle());
        }
    }

    @Override
    public void showData(ShanghaiDetailBean data) {

    }

}
