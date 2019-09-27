package com.study.todayinformation.main.shanghai.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.study.http.result.IResult;
import com.study.todayinformation.base.BasePresenter;
import com.study.todayinformation.base.JHTask;
import com.study.todayinformation.main.shanghai.IShanghaiDetailContract;
import com.study.todayinformation.main.shanghai.dto.ShanghaiDetailBean;
import com.study.todayinformation.main.shanghai.module.ShanghaiDetailHttpTask;

/**
 * @authour lxw
 * @function
 * @date 2019/9/26
 */
public class ShanghaiDetailPresenter extends BasePresenter<IShanghaiDetailContract.IView> implements IShanghaiDetailContract.IPresenter {


    public ShanghaiDetailPresenter(IShanghaiDetailContract.IView view) {
        super(view);
    }

    @Override
    protected IShanghaiDetailContract.IView getEmptyView() {
        return null;
    }

    /**
     * 获取网络数据
     */
    @Override
    public void getNetData() {

        /**
         * 架构师必备条件
         * 1，合理利用继承关系
         * 2，合理利用抽象编程
         * 3，合理利用泛型传递数据
         * 4，合理利用一些设计模式
         */
        submitTask(new JHTask<ShanghaiDetailBean>() {

            @Override
            public IResult<ShanghaiDetailBean> onBackground() {
                return new ShanghaiDetailHttpTask<ShanghaiDetailBean>().getXiaoHuaList("desc", "1", "2");
            }

            @Override
            public void onSuccess(IResult<ShanghaiDetailBean> t) {
                ShanghaiDetailBean data = t.data();
                Gson gson=new Gson();
                gson.toJson(data);
                Log.e("getNetData",gson.toJson(data));
            }

            @Override
            public void onException(Throwable throwable) {
                super.onException(throwable);


            }
        });

//        //数据解析？
//        //相同任务去重？
//        submitTask(new LfTask(){
//
//            //一定要回调到主线程
//            @Override
//            public void onSuccess(Object o) {
//                Log.e("getNetData",o.toString());
//            }
//
//            @Override
//            public void onException(Throwable throwable) {
//
//            }
//
//            //运行子线程
//            @Override
//            public Object onBackground() {
//
//                IResult result = new ShanghaiDetailHttpTask().getXiaoHuaList("desc", "1", "2");
//                return  result;
//            }
//        });
    }




}
