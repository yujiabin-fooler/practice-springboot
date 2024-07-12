package com.jiabin.json.desensitize.practice.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jiabin.yu   2022-09-17 17:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ReadableSensitiveVerify {
    /**
     * mobile、name、idCard；
     * 有需要可以改为枚举
     */
    public String type() default "";
}
