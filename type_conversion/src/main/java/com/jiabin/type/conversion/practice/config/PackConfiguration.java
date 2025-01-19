package com.jiabin.type.conversion.practice.config;

import java.beans.PropertyEditorSupport;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.util.StringUtils;

import com.jiabin.type.conversion.practice.convert.StringToUserConverter;
import com.jiabin.type.conversion.practice.domain.User;

@Configuration
public class PackConfiguration {
  
  // @Bean
  static CustomEditorConfigurer customEditorConfigurer() {
    CustomEditorConfigurer configurer = new CustomEditorConfigurer();
    configurer.setCustomEditors(Map.of(User.class, StrongToUserPropertyEditor.class)) ;
    return configurer ;
  }
  
  public static class StrongToUserPropertyEditor extends PropertyEditorSupport {
    public void setAsText(String text) throws IllegalArgumentException {
      if (StringUtils.hasLength(text)) {
        String[] args = text.split(",") ;
        setValue(new User(args[1], Integer.valueOf(args[0]))) ;
      }
    }
  }

  @Bean
  static BeanFactoryPostProcessor convertPostProcessor() {
    return new BeanFactoryPostProcessor() {
      public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        ConversionService conversionService = beanFactory.getConversionService() ;
        if (conversionService instanceof ConfigurableConversionService cs) {
          cs.addConverter(new StringToUserConverter()) ;
        }
      }
    };
  }
}
