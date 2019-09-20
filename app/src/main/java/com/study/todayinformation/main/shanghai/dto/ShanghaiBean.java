package com.study.todayinformation.main.shanghai.dto;

import java.util.ArrayList;

/**
 * @authour lxw
 * @function
 * @date 2019/9/20
 */
public class ShanghaiBean {
    private int mItemType= IShanghaiItemType.VERTICAL;
    private String mDec;
    private boolean isShowImg;
    private ArrayList<ShanghaiBean> data;

    public int getItemType() {
        return mItemType;
    }

    public ShanghaiBean setItemType(int itemType) {
        mItemType = itemType;
        return this;
    }

    public String getDec() {
        return mDec;
    }

    public ShanghaiBean setDec(String dec) {
        mDec = dec;
        return this;
    }

    public boolean isShowImg() {
        return isShowImg;
    }

    public ShanghaiBean setShowImg(boolean showImg) {
        isShowImg = showImg;
        return this;
    }

    public ArrayList<ShanghaiBean> getData() {
        return data;
    }

    public ShanghaiBean setData(ArrayList<ShanghaiBean> data) {
        this.data = data;
        return this;
    }

    public interface IShanghaiItemType {
        int VERTICAL = 0;
        int HORIZANTAL = 1;
    }

}
