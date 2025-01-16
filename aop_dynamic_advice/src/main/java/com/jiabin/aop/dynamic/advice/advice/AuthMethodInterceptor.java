package com.jiabin.aop.dynamic.advice.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component
public class AuthMethodInterceptor implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    System.err.println("权限认证之前...") ;
    Object ret = invocation.proceed() ;
    System.err.println("权限认证之后...") ;
    return ret ;
  }
}
