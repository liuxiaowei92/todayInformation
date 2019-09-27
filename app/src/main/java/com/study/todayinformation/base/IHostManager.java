package com.study.todayinformation.base;

import com.study.http.request.host.IHost;

/**
 * @authour lxw
 * @function
 * @date 2019/9/25
 */
public interface IHostManager {
    IHost jhHost=new IHost() {

        @Override
        public String getHost() {
            return "http://v.juhe.cn";
        }

        @Override
        public String getDefaultPath() {
            return "/joke/content/list.php";
        }
    };
}
