package com.jiabin.refreshscope.practice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.annotation.AliasFor;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Scope("refresh")
public @interface RefreshScope {
  
  @AliasFor(annotation = Scope.class)
  ScopedProxyMode proxyMode() default ScopedProxyMode.TARGET_CLASS;
}
