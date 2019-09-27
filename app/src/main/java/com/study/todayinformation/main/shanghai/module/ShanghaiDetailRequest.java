package com.study.todayinformation.main.shanghai.module;

import com.study.http.request.IRequest;
import com.study.http.annotation.RequestMethod;
import com.study.todayinformation.base.JHRequest;
import com.study.todayinformation.main.shanghai.dto.ShanghaiDetailBean;

/**
 * @authour lxw
 * @function
 * @date 2019/9/25
 */
public interface ShanghaiDetailRequest {
    IRequest xiaoHuaRequest= JHRequest.sendHttp("/joke/content/list.php", RequestMethod.Get, ShanghaiDetailBean.class);
}
