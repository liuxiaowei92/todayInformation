package com.study.player.source;

import java.io.File;

/**
 * @authour lxw
 * @function
 * @date 2019/11/15
 */
public class RawPlayerSourse implements IPlayerSource {
    private String url;
    private int resId;

    //"android.resource://" + getPackageName() + File.separator + R.raw.splash));
    @Override
    public void setUrl(String url) {
        this.url=url;
    }

    public RawPlayerSourse setPath(String packageName, int rawId) {
       setUrl("android.resource://" + packageName + File.separator + rawId);
       setResId(rawId);
       return this;
    }

    private void setResId(int rawId) {
        this.resId=rawId;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public int getResID() {
        return resId;
    }
}
