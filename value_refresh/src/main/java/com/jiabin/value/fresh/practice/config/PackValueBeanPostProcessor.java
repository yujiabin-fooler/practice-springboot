package com.jiabin.value.fresh.practice.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import com.jiabin.value.fresh.practice.annotation.PackValue;
import com.jiabin.value.fresh.practice.bean.PackValueMetadata;

public class PackValueBeanPostProcessor implements BeanPostProcessor, BeanFactoryAware {
  
  public static final Map<String, Collection<PackValueMetadata>> VALUES = new ConcurrentHashMap<>() ;
  
  private ConfigurableBeanFactory beanFactory ;
  
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    Class<?> clazz = bean.getClass() ;
    ReflectionUtils.doWithLocalFields(clazz, field -> {
      if (field.isAnnotationPresent(PackValue.class)) {
        PackValue packValue = field.getAnnotation(PackValue.class) ;
        String value = packValue.value() ;
        String valueString = value.substring(value.indexOf("${") + 2, value.indexOf("}")) ;
        if (StringUtils.hasLength(value)) {
          Collection<PackValueMetadata> metadatas = VALUES.computeIfAbsent(valueString, key -> new ArrayList<>()) ;
          metadatas.add(new PackValueMetadata(field, bean, this.beanFactory.getTypeConverter())) ;
        }
        // 设置初始值
        String initValue = this.beanFactory.resolveEmbeddedValue(value) ;
        Object val = this.beanFactory.getTypeConverter().convertIfNecessary(initValue, field.getType()) ;
        field.setAccessible(true) ;
        field.set(bean, val) ;
      }
    }) ;
    return bean ;
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    if (beanFactory instanceof ConfigurableBeanFactory bf) {
      this.beanFactory = bf ;
    }
  }
}
