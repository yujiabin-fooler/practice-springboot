package com.jiabin.aop.api.limitation.practice.cache;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.jiabin.aop.api.limitation.practice.annotation.PackLimit;

public class PackCacheBeanPostProcessor implements BeanPostProcessor {

  /**key为限制时长*/
  public static final Map<Long, Cache<String, AtomicInteger>> LIMITS = new ConcurrentHashMap<>() ; 
  
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    ReflectionUtils.doWithMethods(bean.getClass(), method -> {
      PackLimit limit = method.getAnnotation(PackLimit.class) ;
      if (limit != null) {
        TimeUnit unit = limit.unit() ;
        int time = limit.time() ;
        long millis = unit.toMillis(time);
        LIMITS.computeIfAbsent(millis, key -> Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofMillis(millis)).build()) ;
      }
    });
    return bean ;
  }
}
