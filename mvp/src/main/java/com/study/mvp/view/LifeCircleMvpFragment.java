package com.study.mvp.view;

import android.content.Intent;
import android.os.Bundle;

import com.study.mvp.IMvpView;
import com.study.mvp.MvpControler;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @authour lxw
 * @function mvp view层基类  是mvpControler的代理对象
 * @date 2019/9/19
 */
public class LifeCircleMvpFragment extends Fragment implements IMvpView {

    private MvpControler mvpControler;

    @Override
    public MvpControler getMvpControler() {
        if(this.mvpControler==null){
            this.mvpControler=new MvpControler();
        }
        return this.mvpControler;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = this.getArguments();
        if(arguments==null){
            arguments=new Bundle();
        }
        MvpControler mvpControler=this.getMvpControler();
        if(mvpControler!=null){
            mvpControler.onCreate(savedInstanceState,null,arguments);
            mvpControler.onActivityCreated(savedInstanceState,null,arguments);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = this.getArguments();
        if(arguments==null){
            arguments=new Bundle();
        }
        MvpControler mvpControler=this.getMvpControler();
        if(mvpControler!=null){
            mvpControler.onActivityCreated(savedInstanceState,null,arguments);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        MvpControler mvpControler=this.getMvpControler();
        if(mvpControler!=null){
            mvpControler.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MvpControler mvpControler=this.getMvpControler();
        if(mvpControler!=null){
            mvpControler.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MvpControler mvpControler=this.getMvpControler();
        if(mvpControler!=null){
            mvpControler.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        MvpControler mvpControler=this.getMvpControler();
        if(mvpControler!=null){
            mvpControler.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MvpControler mvpControler=this.getMvpControler();
        if(mvpControler!=null){
            mvpControler.onDestroy();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MvpControler mvpControler=this.getMvpControler();
        if(mvpControler!=null){
            mvpControler.onViewDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        MvpControler mvpControler=this.getMvpControler();
        if(mvpControler!=null){
            mvpControler.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MvpControler mvpControler=this.getMvpControler();
        if(mvpControler!=null){
            mvpControler.onActivityResult(requestCode,resultCode,data);
        }
    }

}
