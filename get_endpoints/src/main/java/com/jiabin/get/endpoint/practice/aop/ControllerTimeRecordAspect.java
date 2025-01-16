package com.jiabin.get.endpoint.practice.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerTimeRecordAspect {

  @Pointcut("@annotation(com.jiabin.get.endpoint.practice.aop.TimeRecord)")
  private void record() {
  }
  @Around("record()")
  public Object around(ProceedingJoinPoint pjp) throws Throwable {
    
    long start = System.currentTimeMillis() ;
    Object ret = pjp.proceed() ;
    MethodSignature signature = (MethodSignature) pjp.getSignature() ;
    Method method = signature.getMethod() ;
    String params = Arrays.asList(method.getParameterTypes()).stream().map(type -> type.getSimpleName()).collect(Collectors.joining(",")) ;
    System.err.printf("%s#%s(%s) 接口请求耗时: %dms%n", 
        signature.getDeclaringType().getName(), 
        method.getName(), 
        params, 
        (System.currentTimeMillis() - start)) ;
    return ret ;
  }
}
