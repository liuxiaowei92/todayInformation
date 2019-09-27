package com.study.todayinformation.main.shanghai;

import com.study.mvp.ILifeCircle;
import com.study.mvp.IMvpView;
import com.study.mvp.MvpControler;

/**
 * @authour lxw
 * @function
 * @date 2019/9/26
 */
public interface IShanghaiDetailContract {
    interface IView extends IMvpView{

    }

    interface  IPresenter extends ILifeCircle{

        void getNetData();
    }

    IShanghaiDetailContract.IView emptyView=new IShanghaiDetailContract.IView(){

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };



}
