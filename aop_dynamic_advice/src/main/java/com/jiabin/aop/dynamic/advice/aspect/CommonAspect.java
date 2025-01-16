package com.jiabin.aop.dynamic.advice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.jiabin.aop.dynamic.advice.annotation.PackOperation;

@Component
@Aspect
public class CommonAspect {

  @Pointcut("@annotation(po)")
  private void normal(PackOperation po) {}
  
  @Around("normal(po)")
  public Object around(ProceedingJoinPoint pjp, PackOperation po) throws Throwable {
    System.out.println("通用操作记录...") ;
    Object ret = pjp.proceed() ;
    return ret ;
  }
}
