package com.jiabin.get.endpoint.practice.aop;

import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InnerInterceptorConfig {

  @Bean
  PointcutAdvisor timeRecordAdvisor() {
    AspectJExpressionPointcutAdvisor pointcut = new AspectJExpressionPointcutAdvisor() ;
    pointcut.setExpression("@annotation(com.jiabin.get.endpoint.practice.aop.TimeRecord)") ;
    pointcut.setAdvice(new PerformanceMonitorInterceptor()) ;
    return pointcut ;
  }
}
