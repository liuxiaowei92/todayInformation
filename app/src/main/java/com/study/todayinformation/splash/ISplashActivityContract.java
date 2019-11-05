package com.study.todayinformation.splash;


import com.study.annotation.MvpEmptyViewFactory;
import com.study.mvp.ILifeCircle;
import com.study.mvp.IMvpView;

/**
 * @authour lxw
 * @function
 * @date 2019/9/19
 */
public interface ISplashActivityContract {

    @MvpEmptyViewFactory
    interface Iview extends IMvpView {
        void setTvTimer(String timer);
    }

    interface IPresenter extends ILifeCircle {
        void initTimer();
    }
//    Iview emptyView=new Iview() {
//        @Override
//        public void setTvTimer(String timer) {
//
//        }
//        @Override
//        public MvpControler getMvpControler() {
//            return null;
//        }
//    };
}
