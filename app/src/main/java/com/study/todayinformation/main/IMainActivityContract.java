package com.study.todayinformation.main;


import com.study.annotation.MvpEmptyViewFactory;
import com.study.mvp.ILifeCircle;
import com.study.mvp.IMvpView;

import androidx.fragment.app.Fragment;

/**
 * @authour lxw
 * @function
 * @date 2019/9/19
 */
public interface IMainActivityContract {
    @MvpEmptyViewFactory
    interface Iview extends IMvpView {

        void showFragment(Fragment mFragment);

        void addFragment(Fragment mFragment);

        void hideFragment(Fragment mFragment);
    }
    interface IPresenter extends ILifeCircle {

        void initHomeFragment();

        int getCurrentCheckedId();

        int getCurrentCheckedIndex();

        void replaceFragment(int i);

        int getTopPosition();
        int getBottomPosition();

    }

//    Iview emptyView=new Iview() {
//
//        @Override
//        public void showFragment(Fragment mFragment) {
//
//        }
//
//        @Override
//        public void addFragment(Fragment mFragment) {
//
//        }
//
//        @Override
//        public void hideFragment(Fragment mFragment) {
//
//        }
//
//        @Override
//        public MvpControler getMvpControler() {
//            return null;
//        }
//    };

}
