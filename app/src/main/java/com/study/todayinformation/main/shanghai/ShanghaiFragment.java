package com.study.todayinformation.main.shanghai;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.study.todayinformation.R;
import com.study.todayinformation.base.BaseFragment;
import com.study.todayinformation.base.ViewInject;
import com.study.todayinformation.main.shanghai.adapter.ShanghaiElemAdapter;
import com.study.todayinformation.main.shanghai.dto.ShanghaiDataManager;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @authour lxw
 * @function
 * @date 2019/9/19
 */
@ViewInject(mainLayoutId = R.layout.fragment_shanghai)
public class ShanghaiFragment extends BaseFragment {

    @BindView(R.id.tv_shanghai_welcome)
    TextView tvShanghaiWelcome;
    @BindView(R.id.shanghai_collapsingtoolbarlayout)
    CollapsingToolbarLayout shanghaiCollapsingtoolbarlayout;
    @BindView(R.id.shanghai_app_barlayout)
    AppBarLayout shanghaiAppBarlayout;
    @BindView(R.id.shanghai_recyclerview)
    RecyclerView mRecycleView;
    @Override
    public void afterBindView() {
        initRecycleView();
        initListener();
    }

    private void initRecycleView() {

        mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
        ArrayList<String> datas=new ArrayList<>();
        for(int i=0;i<10;i++){
            datas.add("上海欢迎你"+i);
        }
        mRecycleView.setAdapter(new ShanghaiElemAdapter(mContext, ShanghaiDataManager.getData()));
    }

    private void initListener() {
        shanghaiAppBarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(-verticalOffset<appBarLayout.getMeasuredHeight()/2){
                    //在布局的上半部分
                    tvShanghaiWelcome.setVisibility(View.GONE);
                }else{
                    tvShanghaiWelcome.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
