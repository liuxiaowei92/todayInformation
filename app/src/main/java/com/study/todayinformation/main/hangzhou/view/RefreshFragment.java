package com.study.todayinformation.main.hangzhou.view;

import com.study.refresh.GodRefreshLayout;
import com.study.todayinformation.R;
import com.study.todayinformation.base.BaseFragment;
import com.study.todayinformation.base.ViewInject;
import com.study.todayinformation.main.hangzhou.adapter.ZhiHuAdapter;
import com.study.todayinformation.main.hangzhou.refresh.MeiTuanRefreshManager;
import com.study.todayinformation.main.shanghai.IShanghaiDetailContract;
import com.study.todayinformation.main.shanghai.dto.ShanghaiDetailBean;
import com.study.todayinformation.main.shanghai.presenter.ShanghaiDetailPresenter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @authour lxw
 * @function
 * @date 2019/10/29
 */
@ViewInject(mainLayoutId = R.layout.fragment_refresh)
public class RefreshFragment extends BaseFragment implements IShanghaiDetailContract.IView {
    IShanghaiDetailContract.IPresenter mPresenter = new ShanghaiDetailPresenter(this);

    @BindView(R.id.god_refresh)
    GodRefreshLayout godRefresh;
    @BindView(R.id.refresh_recyclerview)
    RecyclerView refreshRecyclerview;

    @Override
    public void afterBindView() {

        initRefreshLayout();
        initRecyclerView();
    }

    private void initRefreshLayout() {
        //1，采用默认的方式
//        godRefresh.setRefreshManager();
        //2，支持用户自定义
        godRefresh.setRefreshManager(new MeiTuanRefreshManager(mContext));
        godRefresh.setRefreshListener(new GodRefreshLayout.RefreshingListener() {
            @Override
            public void onRefreshing() {
//                mPresenter.getNetData(20);
                //伪代码
                godRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        godRefresh.refreshOver();
                    }
                }, 2000);
            }
        });
    }

    private void initRecyclerView() {
        refreshRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mPresenter.getNetData(20);
    }

    @Override
    public void showData(ShanghaiDetailBean data) {
        if (refreshRecyclerview.getAdapter() == null) {
            ZhiHuAdapter adapter = new ZhiHuAdapter(data.result.data);
            refreshRecyclerview.setAdapter(adapter);

        }
        //加载完成
        godRefresh.refreshOver();
    }
}
