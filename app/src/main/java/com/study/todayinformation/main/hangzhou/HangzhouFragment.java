package com.study.todayinformation.main.hangzhou;

import com.google.android.material.tabs.TabLayout;
import com.study.todayinformation.R;
import com.study.todayinformation.base.BaseFragment;
import com.study.todayinformation.base.ViewInject;
import com.study.todayinformation.main.hangzhou.adapter.HangzhouViewPageAdapter;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @authour lxw
 * @function
 * @date 2019/9/19
 */
@ViewInject(mainLayoutId = R.layout.fragment_hangzhou)
public class HangzhouFragment extends BaseFragment {
    @BindView(R.id.tl_tablayout)
    TabLayout mTablayout;
    @BindView(R.id.vp_viewpager)
    ViewPager mViewpager;

    @Override
    public void afterBindView() {
//        SpannableString spannableString=new SpannableString("杭州");
//        spannableString.setSpan(new UnderlineSpan(),0,spannableString.length(), Spanned.SPAN_USER);

        //相互绑定
        mTablayout.setupWithViewPager(mViewpager);
        //预加载
//        mViewpager.setOffscreenPageLimit(1);
        //Fragment嵌套最好用getChildFragmentManager
        mViewpager.setAdapter(new HangzhouViewPageAdapter(getChildFragmentManager()));

        //FragmentPagerAdapter 会走 onPause onDestroyView  使用少个fragment
        //FragmentStatePagerAdapter 会走 onPause onDestroyView onDestroy onDetach  使用多个 不占内存
    }
}
