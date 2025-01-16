package com.jiabin.guava.practice.rateLimiter;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface PackLimiter {

  public static final String DEFAULT = "default" ;
  
  /**对应使用哪个限流器*/
  String key() default DEFAULT ;
  
  /**每次需要几个许可证*/
  int permit() default 1 ;
}
