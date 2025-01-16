package com.jiabin.aop.api.limitation.practice.aspect;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.jiabin.aop.api.limitation.practice.annotation.PackLimit;
import com.jiabin.aop.api.limitation.practice.cache.PackCacheBeanPostProcessor;
import com.jiabin.aop.api.limitation.practice.exception.PackLimitException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Aspect
public class LimitAspect {
  
  private final Logger logger = LoggerFactory.getLogger(getClass()) ;
  // ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
  private final HttpServletRequest request ;
  public LimitAspect(HttpServletRequest request) {
    this.request = request ;
  }

  @Pointcut("@annotation(limit)")
  private void pclimit(PackLimit limit) {}
  
  @Around("pclimit(limit)")
  public Object around(ProceedingJoinPoint pjp, PackLimit limit) {
    Object ret = null ;
    try {
      ret = pjp.proceed() ;
    } catch (Throwable e) {
      logger.error("发生错误: {}", e) ;
      
      String key = getKey(pjp, limit.key()) ;
      logger.info("缓存key: {}", key) ;
      int count = limit.count() ;
      String message = limit.message() ;
      
      TimeUnit unit = limit.unit() ;
      int time = limit.time() ;
      long millis = unit.toMillis(time);
      
      Map<Long, Cache<String, AtomicInteger>> limits = PackCacheBeanPostProcessor.LIMITS ;
      Cache<String, AtomicInteger> cache = limits.computeIfAbsent(millis, k -> Caffeine.newBuilder()
          .expireAfterWrite(Duration.ofMillis(millis)).build()) ;
      
      AtomicInteger value = cache.get(key, k -> new AtomicInteger(0)) ;
      if (value.intValue() >= count) {
        throw new PackLimitException(message) ;
      }
      value.incrementAndGet() ;
      throw new RuntimeException(e) ;
    }
    
    return ret ;
  }
  
  private String getKey(ProceedingJoinPoint pjp, String key) {
    MethodSignature signature = (MethodSignature) pjp.getSignature() ;
    Method method = signature.getMethod() ;
    Object target = pjp.getTarget() ;
    PackRoot root = new PackRoot(request, target) ;
    MethodBasedEvaluationContext context = new MethodBasedEvaluationContext(root, method, pjp.getArgs(), new DefaultParameterNameDiscoverer()) ;
    context.setVariable("xxxooo", "xxxx00001") ;
    SpelExpressionParser parser = new SpelExpressionParser() ;
    Expression expression = parser.parseExpression(key) ;
    return expression.getValue(context, String.class) ;
  }
  
  @SuppressWarnings("unused")
  private String defaultKey(Class<?> targetType, Method method) {
    StringBuilder builder = new StringBuilder();
    builder.append(targetType.getSimpleName());
    builder.append('#').append(method.getName()).append('(');
    Class<?>[] types = method.getParameterTypes() ;
    for (Class<?> clazz : types) {
      builder.append(clazz.getSimpleName()).append(",") ;
    }
    if (method.getParameterTypes().length > 0) {
      builder.deleteCharAt(builder.length() - 1);
    }
    return (request.getRemoteAddr() + builder.append(')').toString()).replaceAll("[^a-zA-Z0-9]", "") ;
  }
  
  public static class PackRoot {
    private HttpServletRequest request ;
    private Object target ;
    public PackRoot(HttpServletRequest request, Object target) {
      this.request = request ;
      this.target = target ;
    }
    public HttpServletRequest getRequest() {
      return request;
    }
    public Object getTarget() {
      return target;
    }
  }
  
}
