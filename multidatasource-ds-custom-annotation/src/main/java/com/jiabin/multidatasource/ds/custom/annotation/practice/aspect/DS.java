package com.jiabin.multidatasource.ds.custom.annotation.practice.aspect;

import java.lang.annotation.*;

/**
 * 定于数据源切换注解
 *
 * @author jiabin.yu
 * @date 2023/11/27 11:02
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DS {
    // 默认数据源master
    String value() default "master";
}