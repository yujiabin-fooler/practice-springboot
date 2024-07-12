package com.jiabin.jasypt.practice.annotation;

import java.lang.annotation.*;

import static com.jiabin.jasypt.practice.enums.EncryptConstant.ENCRYPT;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptMethod {

    String type() default ENCRYPT;
}

