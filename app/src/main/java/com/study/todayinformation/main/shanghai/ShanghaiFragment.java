package com.study.todayinformation.main.shanghai;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.study.todayinformation.R;
import com.study.todayinformation.base.BaseFragment;
import com.study.todayinformation.base.ViewInject;
import com.study.todayinformation.base.tools.AnimationUtil;
import com.study.todayinformation.base.tools.DoubleClickListener;
import com.study.todayinformation.main.shanghai.adapter.ShanghaiAdapter2;
import com.study.todayinformation.main.shanghai.presenter.PlayerServicePresenter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @authour lxw
 * @function
 * @date 2019/9/19
 */
@ViewInject(mainLayoutId = R.layout.fragment_shanghai)
public class ShanghaiFragment extends BaseFragment implements IPlayerServiceContract.IView {

    IPlayerServiceContract.IPresenter mPresenter=new PlayerServicePresenter(this);
    @BindView(R.id.tv_shanghai_welcome)
    TextView tvShanghaiWelcome;
    @BindView(R.id.tv_marquee)
    TextView mTvTitle;
    @BindView(R.id.shanghai_collapsingtoolbarlayout)
    CollapsingToolbarLayout shanghaiCollapsingtoolbarlayout;
    @BindView(R.id.shanghai_app_barlayout)
    AppBarLayout shanghaiAppBarlayout;
    @BindView(R.id.shanghai_recyclerview)
    RecyclerView mRecycleView;
    private boolean mIsPlaying=false;

    @Override
    public void afterBindView() {
        initRecycleView();
        initListener();
    }

    private void initRecycleView() {

        mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
        //adapter优化
//        mRecycleView.setAdapter(new ShanghaiElemAdapter(getActivity(), ShanghaiDataManager.getData(),false));
        mRecycleView.setAdapter(new ShanghaiAdapter2());
    }

    private void initListener() {
        shanghaiAppBarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(-verticalOffset<appBarLayout.getMeasuredHeight()/2){
                    //在布局的上半部分
                    tvShanghaiWelcome.setVisibility(View.GONE);
                    mTvTitle.setVisibility(View.GONE);
                }else{
                    tvShanghaiWelcome.setVisibility(View.VISIBLE);
                    if(mIsPlaying){
                        mTvTitle.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        tvShanghaiWelcome.setOnClickListener(new DoubleClickListener(new View.OnClickListener() {
            //连续点击后有bug（因为动画是有时间的) 定义DoubleClickListener解决
            @Override
            public void onClick(View v) {

                tvShanghaiWelcome.clearAnimation();
                mTvTitle.clearAnimation();
                if(mIsPlaying){
                    //关闭音视频动画
                    mTvTitle.setVisibility(View.GONE);
                    AnimationUtil.startTranslationXAnim(tvShanghaiWelcome,tvShanghaiWelcome.getTranslationX(),tvShanghaiWelcome.getTranslationX()+150,null);
                    AnimationUtil.startTranslationXAnim(mTvTitle, mTvTitle.getTranslationX(), mTvTitle.getTranslationX() + 150,null);
                    mPresenter.playOrPaused();
                }else{
                    //播放音视频动画
                    AnimationUtil.startTranslationXAnim(tvShanghaiWelcome,tvShanghaiWelcome.getTranslationX(),tvShanghaiWelcome.getTranslationX()-150,null);
                    AnimationUtil.startTranslationXAnim(mTvTitle, mTvTitle.getTranslationX(), mTvTitle.getTranslationX() - 150, new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mTvTitle.setVisibility(View.VISIBLE);
                            //启动service 去播放后台音乐
                            mPresenter.bindService(mContext);

                        }
                    });
                }
                mIsPlaying=!mIsPlaying;
            }
        }));
//        tvShanghaiWelcome.setOnClickListener(new DoubleClickListener2() {
//            @Override
//            protected void onNoDoubleClick(View v) {
//
//            }
//        });
    }
}
