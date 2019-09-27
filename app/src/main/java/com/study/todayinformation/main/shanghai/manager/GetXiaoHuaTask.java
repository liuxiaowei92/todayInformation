package com.study.todayinformation.main.shanghai.manager;

import android.os.AsyncTask;
import android.util.Log;

import com.study.todayinformation.main.shanghai.module.ShanghaiDetailHttpTask;

import java.io.IOException;

import okhttp3.Response;

/**
 * @authour lxw
 * @function
 * @date 2019/9/25
 */
public class GetXiaoHuaTask extends AsyncTask<Object,Object,Object> {

    /**
     * 运行在子线程中
     * @param objects
     * @return
     */
    @Override
    protected Object doInBackground(Object... objects) {
        Object desc = new ShanghaiDetailHttpTask().getXiaoHuaList((String) objects[0], (String) objects[1], (String) objects[2]);
        return desc;
    }

    //运行在主线程 用于更新数据
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Response response= (Response) o;
        try {
            Log.e("GetXiaoHuaTask",response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
