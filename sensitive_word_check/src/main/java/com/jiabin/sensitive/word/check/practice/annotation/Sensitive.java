package com.jiabin.sensitive.word.check.practice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.jiabin.sensitive.word.check.practice.validator.SensitiveValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SensitiveValidator.class)
public @interface Sensitive {
  String value() default "";

  String message() default "{validator.prefix.error}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
  
  /**是否敏感词检查*/
  boolean check() default true ;
  
  /**存在敏感词是抛出异常还是替换敏感词*/
  boolean throwException() default false ;
}