package com.study.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//利用apt编译器自动生成代码 建立mvp空指针保护机制
@Retention(RetentionPolicy.CLASS) //编译期注解
@Target(ElementType.TYPE) //类 接口
public @interface MvpEmptyViewFactory {

}
