package com.jiabin.multidatasource.ds.custom.annotation.practice.aspect;

import com.jiabin.multidatasource.ds.custom.annotation.practice.config.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 实现@DS注解的AOP切面
 *
 * @author jiabin.yu
 * @date 2023/11/27 11:02
 */
@Aspect
@Component
@Slf4j
public class DSAspect {

    @Pointcut("@annotation(com.jiabin.multidatasource.ds.custom.annotation.practice.aspect.DS)")
    public void dynamicDataSource() {
    }

    @Around("dynamicDataSource()")
    public Object datasourceAround(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DS ds = method.getAnnotation(DS.class);
        if (Objects.nonNull(ds)) {
            DataSourceContextHolder.setDataSource(ds.value());
        }
        try {
            return point.proceed();
        } finally {
            DataSourceContextHolder.removeDataSource();
        }
    }
}
