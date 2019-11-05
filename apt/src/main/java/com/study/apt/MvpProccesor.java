package com.study.apt;

import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;

/**
 * 必须是javaLib
 * 专门存放生成这个java代码的注解处理器
 */
//自动在指定路径下生成一个配置文件
@AutoService(Processor.class)
//配置这个类所要处理的注解类型（传入String类型  格式为报名+类名）
@SupportedAnnotationTypes({"com.study.annotation.MvpEmptyViewFactory"})
//jdk版本
//@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MvpProccesor extends AbstractProcessor {
    public Filer mFiler;
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        mFiler=processingEnv.getFiler();
        new MvpEmptyViewProccesor().process(roundEnvironment,this);
        return false;
    }
}
