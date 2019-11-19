package com.study.player.tool;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * @authour lxw
 * @function 读取清单文件中 meta-data 信息
 * @date 2019/11/18
 */
public class DataSourceUtil {

    public static int getMetaDataFromApp(Context context) {
        int value = 0;

        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            value = appInfo.metaData.getInt("playertype");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return value;
    }

}
