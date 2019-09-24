package com.study.todayinformation.main.shanghai.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.study.todayinformation.R;
import com.study.todayinformation.base.BaseActivity;
import com.study.todayinformation.base.ViewInject;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import butterknife.BindView;

/**
 * @authour lxw
 * @function android5.0 以上转场动画
 * @date 2019/9/24
 */
@ViewInject(mainLayoutId = R.layout.activity_shanghai_detail)
public class ShanghaiDetailActivity extends BaseActivity {

    public static String mActivityOptionsCompt="ShanghaiDetailActivity";
    @BindView(R.id.iv_shanghai_detail)
    ImageView ivShanghaiDetail;

    @Override
    public void afterBindView() {
        initAnima();
    }

    private void initAnima() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){

            //布局文件配置后 可以去掉
            //ViewCompat.setTransitionName(ivShanghaiDetail,mActivityOptionsCompt);
            //开启转场动画
            startPostponedEnterTransition();
        }
    }

    /**
     * 5.0系统的转场动画：共享元素动画
     */
    public static void start_5_0(Activity activity,View view){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Intent intent=new Intent(activity,ShanghaiDetailActivity.class);

            Pair pair = new Pair(view,mActivityOptionsCompt);
            ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(activity,pair);
            ActivityCompat.startActivity(activity,intent,optionsCompat.toBundle());
        }
    }

}
