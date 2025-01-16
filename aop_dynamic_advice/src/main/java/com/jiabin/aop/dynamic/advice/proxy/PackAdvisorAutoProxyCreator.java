package com.jiabin.aop.dynamic.advice.proxy;

import java.lang.reflect.Method;
import java.util.List;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;
import org.springframework.aop.support.StaticMethodMatcher;

public class PackAdvisorAutoProxyCreator extends AbstractAdvisorAutoProxyCreator {

  private static final long serialVersionUID = 1L ;

  @Override
  protected Object createProxy(Class<?> beanClass, String beanName, Object[] specificInterceptors,
      TargetSource targetSource) {
    return super.createProxy(beanClass, beanName, specificInterceptors, targetSource);
  }

  @Override
  protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource targetSource) {
    if (beanClass.getPackageName().startsWith("com.pack")) {
      return new Object[] {new CommonAdvisor()} ;
    }
    return null ;
  }
  
  private static class CommonAdvisor implements PointcutAdvisor {
    private static final List<String> EXCLUDES = List.of("equals", "hashCode", "toString") ;
    public Advice getAdvice() {
      return new MethodInterceptor() {
        public Object invoke(MethodInvocation invocation) throws Throwable {
          System.out.println("通用默认通知...") ;
          return invocation.proceed() ;
        }
      } ;
    }

    @Override
    public boolean isPerInstance() {
      return false;
    }

    public Pointcut getPointcut() {
      return new Pointcut() {
        public MethodMatcher getMethodMatcher() {
          return new StaticMethodMatcher() {
            public boolean matches(Method method, Class<?> targetClass) {
              String methodName = method.getName() ;
              return !EXCLUDES.contains(methodName)  ;
            }
          }; 
        }
        public ClassFilter getClassFilter() {
          return new ClassFilter() {
            public boolean matches(Class<?> clazz) {
              System.err.println(clazz) ;
              return clazz.getPackageName().startsWith("com.pack") ;
            }
          } ;
        }
      } ;
    }
  }

}
