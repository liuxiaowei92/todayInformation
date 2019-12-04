package com.study.todayinformation.main.beijing;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;

import com.study.todayinformation.R;
import com.study.todayinformation.base.BaseFragment;
import com.study.todayinformation.base.ViewInject;
import com.study.todayinformation.main.shanghai.view.ShanghaiDetailActivity;

import butterknife.BindView;

/**
 * @authour lxw
 * @function
 * @date 2019/9/19
 */
@ViewInject(mainLayoutId = R.layout.fragment_beijing)
public class BeijingFragment extends BaseFragment {
    @BindView(R.id.bt_play)
    Button tvClick;
//    private ProcessDataReceiver mProcessDataReceiver;

    @Override
    public void afterBindView() {
        //android8.0启动服务适配
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            mContext.startForegroundService(new Intent(mContext, MainProcessService.class));
        }else {
            mContext.startService(new Intent(mContext, MainProcessService.class));
        }
        tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProcessDataTest.getInstance().setProcessDec("我来自北京");
                ShanghaiDetailActivity.start_5_0(getActivity(),tvClick);
            }
        });

        //广播方式
//        mProcessDataReceiver = new ProcessDataReceiver();
//        getActivity().registerReceiver(mProcessDataReceiver,new IntentFilter("shanghai_get_process_data"));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.stopService(new Intent(mContext,MainProcessService.class));
        //广播方式
//        getActivity().unregisterReceiver(mProcessDataReceiver);
    }

    //广播方式
//    private class ProcessDataReceiver extends BroadcastReceiver{
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String processDec = ProcessDataTest.getInstance().getProcessDec();
//            Intent postIntent = new Intent("beijing_post_process_data");
//            postIntent.putExtra("processDec",processDec);
//            getActivity().sendBroadcast(postIntent);
//        }
//    }
}
