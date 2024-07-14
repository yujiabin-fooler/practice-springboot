package com.jiabin.multidatasource.read.write.sperate.practice.common.annotation;

import com.jiabin.multidatasource.read.write.sperate.practice.common.constant.DataSourceConst;

import java.lang.annotation.*;

/**
 * @Description: 数据源路由注解
 * @Author jiabin.yu
 * @Date: 2022/2/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceRouting {

    String value() default DataSourceConst.DATASOURCE_MASTER;

}
