package com.study.todayinformation.base;

import com.study.mvp.IMvpView;
import com.study.mvp.base.BaseMvpPresenter;
import com.study.task.LfTask;
import com.study.task.TaskHelper;

/**
 * @authour lxw
 * @function 集成mvp 及 网络请求快捷方式
 * @date 2019/9/26
 */
public abstract class BasePresenter<T extends IMvpView> extends BaseMvpPresenter<T> {

    public BasePresenter(T view) {
        super(view);
    }

    public void submitTask(LfTask task){
        TaskHelper.submitTask(task,task);
    }
}
