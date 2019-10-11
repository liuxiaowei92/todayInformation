package com.study.todayinformation.main.hangzhou.view;

import com.google.android.material.appbar.AppBarLayout;
import com.study.todayinformation.R;
import com.study.todayinformation.base.BaseFragment;
import com.study.todayinformation.base.ViewInject;
import com.study.todayinformation.main.hangzhou.adapter.ZhiHuAdapter;
import com.study.todayinformation.main.shanghai.IShanghaiDetailContract;
import com.study.todayinformation.main.shanghai.dto.ShanghaiDetailBean;
import com.study.todayinformation.main.shanghai.presenter.ShanghaiDetailPresenter;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @authour lxw
 * @function
 * @date 2019/10/11
 */
@ViewInject(mainLayoutId = R.layout.fragment_zhihu)
public class ZhiHuFragment extends BaseFragment implements IShanghaiDetailContract.IView {
    IShanghaiDetailContract.IPresenter mPresenter=new ShanghaiDetailPresenter(this);
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.zhihu_app_barlayout)
    AppBarLayout zhihuAppBarlayout;
    @BindView(R.id.zhihu_recyclerview)
    RecyclerView zhihuRecyclerview;

    @Override
    public void afterBindView() {

        zhihuRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mPresenter.getNetData(20);

    }

    @Override
    public void showData(ShanghaiDetailBean data) {
        if(zhihuRecyclerview.getAdapter()==null){
            ZhiHuAdapter adapter=new ZhiHuAdapter(data.result.data);
            zhihuRecyclerview.setAdapter(adapter);
        }
    }
}