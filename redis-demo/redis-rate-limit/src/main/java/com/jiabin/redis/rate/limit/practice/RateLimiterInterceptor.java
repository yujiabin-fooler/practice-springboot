package com.jiabin.redis.rate.limit.practice;

import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class RateLimiterInterceptor implements HandlerInterceptor {

    private final RedisTemplate<String, Integer> redisTemplate;
    private final String rateLimitKeyPrefix = "rate_limit:";

    public RateLimiterInterceptor(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String ip = request.getRemoteAddr();
        String methodName = ((MethodSignature) (handler)).getMethod().getName();
        String rateLimitKey = rateLimitKeyPrefix + methodName + ":" + ip;

        Long currentCount = redisTemplate.opsForValue().increment(rateLimitKey);
        if (currentCount > 1) {
            // 如果当前计数大于1，则说明请求已超过限制
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE,
                    "Too many requests, please try again later.");
            return false;
        }

        // 设置过期时间
        redisTemplate.expire(rateLimitKey, RateLimit.class.cast((
                (MethodSignature) handler).getMethod().getAnnotation(RateLimit.class)).timeout(), TimeUnit.SECONDS);
        return true;
    }
}
