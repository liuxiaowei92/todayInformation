package com.study.todayinformation.main.shanghai.dto;

import java.util.ArrayList;

/**
 * @authour lxw
 * @function 数据
 * @date 2019/9/20
 */
public class ShanghaiDataManager {
    /**
     * 获取竖向数据
     * @param len
     * @return
     */
    private static ArrayList<ShanghaiBean> getVerData(int len){
        ArrayList<ShanghaiBean> data=new ArrayList<>();
        for(int i=0;i<len;i++){
            ShanghaiBean bean=new ShanghaiBean();
            bean.setShowImg(false).setDec("上海欢迎您");
            data.add(bean);
        }
        return data;
    }

    /**
     * 获取横向数据
     * @param len
     * @return
     */
    private static ArrayList<ShanghaiBean> getHorData(int len){
        ArrayList<ShanghaiBean> data=new ArrayList<>();
        for(int i=0;i<len;i++){
            ShanghaiBean bean=new ShanghaiBean();
            bean.setShowImg(true).setDec("上海魔都");
            data.add(bean);
        }
        return data;
    }


    /**
     * 返回整合好的数据
     * @return
     */
    public static ArrayList<ShanghaiBean> getData(){
        ArrayList<ShanghaiBean> data=new ArrayList<>();
        data.addAll(getVerData(5));
        data.add(new ShanghaiBean().setData(getHorData(10))
                .setItemType(ShanghaiBean.IShanghaiItemType.HORIZANTAL));
        data.addAll(getVerData(5));
        data.add(new ShanghaiBean().setData(getHorData(10))
                .setItemType(ShanghaiBean.IShanghaiItemType.HORIZANTAL));
        return data;
    }
}
