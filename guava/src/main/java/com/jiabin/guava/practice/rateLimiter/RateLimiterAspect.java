package com.jiabin.guava.practice.rateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.RateLimiter;

import jakarta.annotation.PostConstruct;

@Component
@Aspect
public class RateLimiterAspect {

  private static final Map<String, RateLimiter> limiters = new ConcurrentHashMap<>() ;
  
  @PostConstruct
  public void init() {
    limiters.put(PackLimiter.DEFAULT, RateLimiter.create(2)) ;
    limiters.put("xxx", RateLimiter.create(5)) ;
    limiters.put("ooo", RateLimiter.create(10)) ;
  }
  
  @Pointcut("@annotation(limiter)")
  private void limit(PackLimiter limiter) {}
  
  @Around("limit(limiter)")
  public Object before(ProceedingJoinPoint pjp, PackLimiter limiter) throws Throwable {
    String key = limiter.key() ;
    int permit = limiter.permit() ;
    RateLimiter rateLimiter = limiters.computeIfAbsent(key, k -> RateLimiter.create(10)) ;
    if (!rateLimiter.tryAcquire(permit)) {
      throw new RateLimiterException("你的访问速度太快了") ;
    }
    return pjp.proceed() ;
  }
}
