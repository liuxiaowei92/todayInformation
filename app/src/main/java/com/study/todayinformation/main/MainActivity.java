package com.study.todayinformation.main;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.study.todayinformation.R;
import com.study.todayinformation.base.BaseActivity;
import com.study.todayinformation.base.ViewInject;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Administrator
 */
@ViewInject(mainLayoutId = R.layout.activity_main)
public class MainActivity extends BaseActivity implements IMainActivityContract.Iview {

    IMainActivityContract.IPresenter mPresenter = new MainActivityPresenter(this);
    @BindView(R.id.fac_main_home)
    Button facMainHome;
    @BindView(R.id.rb_main_shanghai)
    RadioButton rbMainShanghai;
    @BindView(R.id.rb_main_hangzhou)
    RadioButton rbMainHangzhou;
    @BindView(R.id.rg_main_top)
    RadioGroup rgMainTop;
    @BindView(R.id.rb_main_beijing)
    RadioButton rbMainBeijing;
    @BindView(R.id.rb_main_shenzhen)
    RadioButton rbMainShenzhen;
    @BindView(R.id.rg_main_bottom)
    RadioGroup rgMainBottom;
    private boolean isChangeTopOrBottom;

    @Override
    public void afterBindView() {
        initHomeFragment();
        changeAnima(rgMainBottom, rgMainTop);
        initCheckListener();
    }

    //初始化
    private void initHomeFragment() {
        mPresenter.initHomeFragment();
    }

    private void initCheckListener() {
        rgMainTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==mPresenter.getCurrentCheckedId()){
                    return;
                }
                switch (checkedId){
                    case R.id.rb_main_shanghai:
                        mPresenter.replaceFragment(0);
                        break;
                    case R.id.rb_main_hangzhou:
                        mPresenter.replaceFragment(1);
                        break;
                        default:break;
                }
            }
        });

        rgMainBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==mPresenter.getCurrentCheckedId()){
                    return;
                }
                switch (checkedId){
                    case R.id.rb_main_beijing:
                        mPresenter.replaceFragment(2);
                        break;
                    case R.id.rb_main_shenzhen:
                        mPresenter.replaceFragment(3);
                        break;
                    default:break;
                }

            }
        });
    }
    @OnClick(R.id.fac_main_home)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fac_main_home:
                isChangeTopOrBottom = !isChangeTopOrBottom;
                if (isChangeTopOrBottom) {
                    changeAnima(rgMainTop, rgMainBottom);
                    handleTopPosition();
                } else {
                    changeAnima(rgMainBottom, rgMainTop);
                    handleBottomPosition();
                }
                break;
            default:
                break;
        }
    }

    //北京 深圳
    private void handleBottomPosition() {
        if (mPresenter.getTopPosition() != 1) {
            mPresenter.replaceFragment(0);
            rbMainShanghai.setChecked(true);
        } else {
            mPresenter.replaceFragment(1);
            rbMainHangzhou.setChecked(true);
        }
    }

    //上海 杭州
    private void handleTopPosition() {
        if (mPresenter.getBottomPosition() != 3) {//深圳
            mPresenter.replaceFragment(2);
            rbMainBeijing.setChecked(true);
        } else {
            mPresenter.replaceFragment(3);
            rbMainShenzhen.setChecked(true);
        }
    }

    private void changeAnima(RadioGroup gone, RadioGroup show) {
        //消失的动画
        gone.clearAnimation();//清除自身动画
        Animation animationGone = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.GONE);

        //展示的动画
        show.clearAnimation();
        Animation animationShow = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_show);
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().show(mFragment).commit();
    }

    @Override
    public void addFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_content, mFragment).commit();
    }

    @Override
    public void hideFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
    }
}
