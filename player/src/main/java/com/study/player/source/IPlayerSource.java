package com.study.player.source;

/**
 * @authour lxw
 * @function 播放源
 * @date 2019/11/15
 */
public interface IPlayerSource {

    void setUrl(String url);

    String getUrl();

    int getResID();
}
