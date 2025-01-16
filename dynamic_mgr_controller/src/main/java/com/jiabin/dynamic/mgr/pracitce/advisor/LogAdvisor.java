package com.jiabin.dynamic.mgr.pracitce.advisor;

import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.InitializingBean;

public class LogAdvisor implements PointcutAdvisor, InitializingBean {
  public Advice getAdvice() {
    return new MethodInterceptor() {
      public Object invoke(MethodInvocation invocation) throws Throwable {
        System.err.println("Log...") ;
        return invocation.proceed() ;
      }
    };
  }

  public Pointcut getPointcut() {
    return new StaticMethodMatcherPointcut() {
      @Override
      public boolean matches(Method method, Class<?> targetClass) {
        return true ;
      }
    } ;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.err.println("Logger init...") ;
  }
}
