package com.jiabin.aop.api.limitation.practice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PackLimit {

  /**最多重试次数；默认3次*/
  int count() default 3 ;
  
  /**缓存key，支持SpEL表达式*/
  String key() default "" ;
  
  /**限制的时间*/
  int time() default 3 ;
  
  /**显示时间范围内不允许调用*/
  TimeUnit unit() default TimeUnit.SECONDS ;
  
  /**被限制后提示信息*/
  String message() default "不允许操作" ;
}
