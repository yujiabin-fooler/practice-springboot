package com.jiabin.json.desensitize.practice.config.annotation;

import com.jiabin.json.desensitize.practice.config.DataMaskingFunc;

import java.lang.annotation.*;

/**
 * @author jiabin.yu   2022-09-17 23:05
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataMasking {

    DataMaskingFunc maskFunc() default DataMaskingFunc.NO_MASK;
}

