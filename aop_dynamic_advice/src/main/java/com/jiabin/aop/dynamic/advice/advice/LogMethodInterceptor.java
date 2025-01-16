package com.jiabin.aop.dynamic.advice.advice;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogMethodInterceptor implements MethodInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(LogMethodInterceptor.class) ;
  
  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    Method method = invocation.getMethod();
    logger.info("执行方法: {}", method) ;
    Object ret = invocation.proceed() ;
    System.err.println("方法执行之后...") ;
    return ret ;
  }
}
