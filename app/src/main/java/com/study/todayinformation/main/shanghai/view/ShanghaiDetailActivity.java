package com.study.todayinformation.main.shanghai.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.study.todayinformation.R;
import com.study.todayinformation.base.BaseActivity;
import com.study.todayinformation.base.ViewInject;
import com.study.todayinformation.main.shanghai.IShanghaiDetailContract;
import com.study.todayinformation.main.shanghai.dto.ShanghaiDetailBean;
import com.study.todayinformation.main.shanghai.presenter.ShanghaiDetailPresenter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

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

    @Override
    public void afterBindView() {
        initAnima();
        initGetNetData();
//        initPostNetData();
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
