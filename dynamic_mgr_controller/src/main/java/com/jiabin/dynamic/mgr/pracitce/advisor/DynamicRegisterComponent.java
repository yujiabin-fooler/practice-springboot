package com.jiabin.dynamic.mgr.pracitce.advisor;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class DynamicRegisterComponent implements ApplicationContextAware {

  private ApplicationContext context ;
  private final Map<Class<?>, Object> createdBean = new ConcurrentHashMap<>() ;
  private final Map<Class<?>, Object> originObject = new ConcurrentHashMap<>() ;
  
  public <T> void register(Class<T> clazz) {
    try {
      AutowireCapableBeanFactory beanFactory = context.getAutowireCapableBeanFactory() ;
      Object proxy = createdBean.get(clazz) ;
      if (proxy == null) {
        // 1.清理缓存，否则动态创建的对象在执行初始化流程时无法为其创建代理对象
        Field field = AbstractAdvisorAutoProxyCreator.class.getDeclaredField("advisorRetrievalHelper") ;
        field.setAccessible(true) ;
        AnnotationAwareAspectJAutoProxyCreator bean = context.getBean(AnnotationAwareAspectJAutoProxyCreator.class) ;
        Object instance = field.get(bean) ;
        field = field.getType().getDeclaredField("cachedAdvisorBeanNames") ;
        field.setAccessible(true) ;
        field.set(instance, null) ;
  
        // 2.注册切面
        LogAdvisor logAdvisor = new LogAdvisor() ;
        beanFactory.autowireBean(logAdvisor) ;
        beanFactory.initializeBean(logAdvisor, UUID.randomUUID().toString()) ;
        if (beanFactory instanceof DefaultListableBeanFactory bf) {
          bf.registerSingleton("logAdvisor", logAdvisor) ;
        }
        
        // 3.手动创建对象并执行bean创建的生命周期钩子函数
        proxy = beanFactory.createBean(clazz) ;
        createdBean.put(clazz, proxy) ;
      }
      
      // 4.替换所有引用(依赖)了当前给定类对象的实例
      if (beanFactory instanceof DefaultListableBeanFactory bf) {
        String[] arr = bf.getDependentBeans("userService") ;
        for (String beanClass : arr) {
          Class<?> cls = null ;
          try {
            cls = Class.forName(beanClass) ;
          } catch (ClassNotFoundException ex) {
            Object obj = context.getBean(beanClass) ;
            cls = obj.getClass() ;
          }
          if (cls == null) {
            continue ;
          }
          Object obj = context.getBean(cls) ;
          Field[] fields = cls.getDeclaredFields();
          for (Field f : fields) {
            if (f.getType() == clazz) {
              f.setAccessible(true) ;
              Object origin = f.get(obj) ;
              originObject.put(clazz, origin) ;
              f.set(obj, proxy) ;
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace() ;
    }
  }
  
  public <T> void restore(Class<T> clazz, String advisor) {
    // 1.清理缓存，否则动态创建的对象在执行初始化流程时无法为其创建代理对象
    AutowireCapableBeanFactory beanFactory = context.getAutowireCapableBeanFactory() ;
//    try {
//      Field field = AbstractAdvisorAutoProxyCreator.class.getDeclaredField("advisorRetrievalHelper") ;
//      field.setAccessible(true) ;
//      AnnotationAwareAspectJAutoProxyCreator bean = context.getBean(AnnotationAwareAspectJAutoProxyCreator.class) ;
//      Object instance = field.get(bean) ;
//      field = field.getType().getDeclaredField("cachedAdvisorBeanNames") ;
//      field.setAccessible(true) ;
//      field.set(instance, null) ;
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    
//    
//    // 2.从单例池中删除切面
//    if (beanFactory instanceof PackBeanFactory pbf) {
//      pbf.removeSingleton(advisor) ;
//    }
//    
//    // 3.创建单例对象
//    T proxy = beanFactory.createBean(clazz) ;
    Object origin = this.originObject.get(clazz) ;
    if (origin == null) {
      return ;
    }
    if (beanFactory instanceof DefaultListableBeanFactory bf) {
      String[] arr = bf.getDependentBeans("userService") ;
      for (String beanClass : arr) {
        Class<?> cls = null ;
        try {
          cls = Class.forName(beanClass) ;
        } catch (ClassNotFoundException ex) {
          Object obj = context.getBean(beanClass) ;
          cls = obj.getClass() ;
        }
        if (cls == null) {
          continue ;
        }
        Object obj = context.getBean(cls) ;
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
          if (f.getType() == clazz) {
            f.setAccessible(true) ;
            try {
              f.set(obj, origin) ;
            } catch (Exception e) {
              e.printStackTrace() ;
            }
          }
        }
      }
    }
  }
  
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext ;
  }
}
