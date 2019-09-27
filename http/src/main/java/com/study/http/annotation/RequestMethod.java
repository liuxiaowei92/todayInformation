package com.study.http.annotation;

import androidx.annotation.IntDef;

import static com.study.http.annotation.RequestMethod.Get;
import static com.study.http.annotation.RequestMethod.Post;

/**
 * @authour lxw
 * @function
 * @date 2019/9/25
 */
@IntDef({Get,Post})
public @interface RequestMethod {
    int Get=1;
    int Post=2;
}