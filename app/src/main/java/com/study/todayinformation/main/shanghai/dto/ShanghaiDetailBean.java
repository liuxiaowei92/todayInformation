package com.study.todayinformation.main.shanghai.dto;

import java.util.ArrayList;

/**
 * @authour lxw
 * @function
 * @date 2019/9/27
 */
public class ShanghaiDetailBean {
    public int error_code;
    public String resson;
    public XiaoHuaListBean result;

    public class XiaoHuaListBean {
        public ArrayList<XiaoHuaBean> data;
    }

    public class XiaoHuaBean {
        public String content;
        public String hashId;
        public String unixtime;
        public String updatetime;

    }
}
