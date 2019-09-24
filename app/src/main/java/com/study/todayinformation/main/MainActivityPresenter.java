package com.study.todayinformation.main;

import com.study.todayinformation.R;
import com.study.todayinformation.main.beijing.BeijingFragment;
import com.study.todayinformation.main.hangzhou.HangzhouFragment;
import com.study.todayinformation.main.shanghai.ShanghaiFragment;
import com.study.todayinformation.main.shenzhen.ShenzhenFragment;
import com.study.mvp.base.BaseMvpPresenter;

import androidx.fragment.app.Fragment;

/**
 * @authour lxw
 * @function
 * @date 2019/9/19
 */
public class MainActivityPresenter extends BaseMvpPresenter<IMainActivityContract.Iview> implements IMainActivityContract.IPresenter {


    //当前fragment角标
    private int mCurrentFragmentIndex;
    private Fragment[] mFragments=new Fragment[4];
    private int mCurrentCheckedId;
    private int mTopPosition;
    private int mBottomPosition;

    public MainActivityPresenter(IMainActivityContract.Iview view) {
        super(view);
    }

    @Override
    protected IMainActivityContract.Iview getEmptyView() {
        return null;

    }

    @Override
    public void initHomeFragment() {
        mCurrentFragmentIndex=0;
        replaceFragment(mCurrentFragmentIndex);
    }

    @Override
    public int getCurrentCheckedId() {
        return mCurrentCheckedId;
    }

    @Override
    public int getCurrentCheckedIndex() {
        return mCurrentFragmentIndex;
    }

    @Override
    public void replaceFragment(int mCurrentFragmentIndex) {
        for(int i=0;i<mFragments.length;i++){
            if(mCurrentFragmentIndex!=i){
               if( mFragments[i]!=null){
                    hideFragment(mFragments[i]);
                }
            }
        }
        Fragment mFragment=mFragments[mCurrentFragmentIndex];
        if(mFragment!=null){
            addAndShowFragment(mFragment);
            setCurChecked(mCurrentFragmentIndex);
        }else{
            newCurretFragment(mCurrentFragmentIndex);
            setCurChecked(mCurrentFragmentIndex);
        }
    }

    @Override
    public int getTopPosition() {
        return mTopPosition;
    }

    @Override
    public int getBottomPosition() {
        return mBottomPosition;
    }

    //记录当前角标
    private void setCurChecked(int mCurrentFragmentIndex) {
        this.mCurrentFragmentIndex=mCurrentFragmentIndex;
        switch (mCurrentFragmentIndex){
            case 0:
                mCurrentCheckedId= R.id.rb_main_shanghai;
                mTopPosition=0;
                break;
            case 1:
                mCurrentCheckedId= R.id.rb_main_hangzhou;
                mTopPosition=1;
                break;
            case 2:
                mCurrentCheckedId= R.id.rb_main_beijing;
                mBottomPosition=2;
                break;
            case 3:
                mCurrentCheckedId= R.id.rb_main_shenzhen;
                mBottomPosition=3;
                break;
                default:
        }
    }

    //创建当前的Fragment
    private void newCurretFragment(int mCurrentFragmentIndex) {
        Fragment fragment=null;
        switch (mCurrentFragmentIndex){
            case 0:
                fragment=new ShanghaiFragment();
                break;
            case 1:
                fragment=new HangzhouFragment();
                break;
            case 2:
                fragment=new BeijingFragment();
                break;
            case 3:
                fragment=new ShenzhenFragment();
                break;
                default:
        }

        mFragments[mCurrentFragmentIndex]=fragment;
        addAndShowFragment(fragment);
    }

    //显示Fragment
    private void addAndShowFragment(Fragment mFragment) {
        if(mFragment.isAdded()){
            getView().showFragment(mFragment);
        }else{
            getView().addFragment(mFragment);
        }
    }

    //隐藏 Fragmet
    private void hideFragment(Fragment mFragment) {
        if(mFragment!=null && mFragment.isVisible()){
            getView().hideFragment(mFragment);
        }
    }
}
