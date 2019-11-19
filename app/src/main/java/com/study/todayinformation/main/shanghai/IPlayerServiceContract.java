package com.study.todayinformation.main.shanghai;

import android.content.Context;

import com.study.annotation.MvpEmptyViewFactory;
import com.study.mvp.ILifeCircle;
import com.study.mvp.IMvpView;

/**
 * @authour lxw
 * @function
 * @date 2019/9/26
 */
public interface IPlayerServiceContract {
    @MvpEmptyViewFactory
    interface IView extends IMvpView {

    }

    interface IPresenter extends ILifeCircle {
       void bindService(Context context);

        void playOrPaused();
    }


}
