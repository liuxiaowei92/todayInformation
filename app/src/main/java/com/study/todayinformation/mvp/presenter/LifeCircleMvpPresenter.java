package com.study.todayinformation.mvp.presenter;

import com.study.todayinformation.mvp.ILifeCircle;
import com.study.todayinformation.mvp.IMvpView;
import com.study.todayinformation.mvp.MvpControler;

import java.lang.ref.WeakReference;

/**
 * @authour lxw
 * @function P层 基类
 * @date 2019/9/19
 */
public abstract class LifeCircleMvpPresenter<T extends IMvpView> implements ILifeCircle {

    //弱引用
    protected WeakReference<T> weakReference;

    protected LifeCircleMvpPresenter() {
        super();
    }

    public LifeCircleMvpPresenter(IMvpView iMvpView) {
        super();
        attachView(iMvpView);
        MvpControler mvpControler = iMvpView.getMvpControler();
        mvpControler.savePresenter(this);
    }

    @Override
    public void attachView(IMvpView iMvpView) {
        if (weakReference == null) {
            //创建弱引用IMvpView
            weakReference = new WeakReference(iMvpView);
        } else {
            T view = (T) weakReference.get();
            if (view != iMvpView) {
                weakReference = new WeakReference(iMvpView);
            }
        }
    }

    @Override
    public void onDestroy() {
        weakReference = null;
    }

    //获取view的方法
    protected T getView() {

        T view = weakReference != null ? (T) weakReference.get() : null;
        if (view == null) {
            return getEmptyView();
        }
        return view;
    }

    protected abstract T getEmptyView();

}
