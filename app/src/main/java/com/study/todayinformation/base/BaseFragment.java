package com.study.todayinformation.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.todayinformation.mvp.view.LifeCircleMvpFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;

/**
 * @authour lxw
 * @function
 * @date 2019/9/18
 */
public abstract class BaseFragment extends LifeCircleMvpFragment {

//    //可以获取到Context
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=null;
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null) {
            int mainLayoutId = annotation.mainLayoutId();
            if (mainLayoutId > 0) {
                mView=initFragmentView(inflater,mainLayoutId);
                //butterKnife初始化
                ButterKnife.bind(this,mView);
                afterBindView();
            } else {
                throw new RuntimeException("annotation < 0");
            }
        } else {
            throw new RuntimeException("annotation = null");
        }
        return mView;
    }

    protected  View initFragmentView( LayoutInflater inflater,int mainLayoutId){
        return inflater.inflate(mainLayoutId,null);
    }

    /**
     * 模板方法设计模式
     */
    public abstract void afterBindView();
}
