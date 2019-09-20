package com.study.todayinformation.main.tools;

import androidx.annotation.IntDef;

import static com.study.todayinformation.main.tools.MainConstantTool.BEIJING;
import static com.study.todayinformation.main.tools.MainConstantTool.HANGZHOU;
import static com.study.todayinformation.main.tools.MainConstantTool.SHANGHAI;
import static com.study.todayinformation.main.tools.MainConstantTool.SHENZHEN;

/**
 * @authour lxw
 * @function 常量工具包
 * @date 2019/9/20
 */

@IntDef({SHANGHAI,HANGZHOU,BEIJING,SHENZHEN})
public @interface MainConstantTool {
    int SHANGHAI=0;
    int HANGZHOU=1;
    int BEIJING =2;
    int SHENZHEN=3;
}
