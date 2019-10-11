package com.study.todayinformation.main.shanghai;

import com.study.mvp.ILifeCircle;
import com.study.mvp.IMvpView;
import com.study.mvp.MvpControler;
import com.study.todayinformation.main.shanghai.dto.ShanghaiDetailBean;

/**
 * @authour lxw
 * @function
 * @date 2019/9/26
 */
public interface IShanghaiDetailContract {
    interface IView extends IMvpView{

        void showData(ShanghaiDetailBean data);
    }

    interface  IPresenter extends ILifeCircle{

        void getNetData(int pageSize);
    }

    //以后优化  ept方式
    IShanghaiDetailContract.IView emptyView=new IShanghaiDetailContract.IView(){

        @Override
        public void showData(ShanghaiDetailBean data) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };



}
