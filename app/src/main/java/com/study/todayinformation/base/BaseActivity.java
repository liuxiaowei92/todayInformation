package com.study.todayinformation.base;

import android.os.Bundle;

import com.study.todayinformation.mvp.view.LifeCircleMvpActivity;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;

/**
 * @authour lxw
 * @function
 * @date 2019/9/18
 */
public abstract class BaseActivity extends LifeCircleMvpActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null) {
            int mainLayoutId = annotation.mainLayoutId();
            if (mainLayoutId > 0) {
                setContentView(mainLayoutId);
                //butterKnife初始化
                ButterKnife.bind(this);
                afterBindView();
            } else {
                throw new RuntimeException("annotation < 0");
            }
        } else {
            throw new RuntimeException("annotation = null");
        }
    }

    /**
     * 模板方法设计模式
     */
    public abstract void afterBindView();
}
