package com.jiabin.jackson.practice.custom_annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveSerializer.class)
public @interface Sensitive {

	/**
	 * 起始位置
	 * @return
	 */
	int start() default 0;

	/**
	 * 结束位置
	 * @return
	 */
	int end() default 0;
	
	/**
   * 掩码
   * 
   * @return
   */
  String mask() default "*";
}