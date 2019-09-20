package com.study.todayinformation.base;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @authour lxw
 * @function setContentView 注解
 * @date 2019/9/19
 */
@Retention(RUNTIME) //运行时注解 性能没有class好
@Target(TYPE) //类 接口 注解
public @interface ViewInject {
    int mainLayoutId() default -1;
}
