package com.jiabin.qulifier.practice.utils;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.jiabin.qulifier.practice.annotation.PackQualifier;
import com.jiabin.qulifier.practice.component.DAO;

import jakarta.annotation.PostConstruct;

@Component
public class PackQualifierBeans implements ApplicationContextAware {

  private ApplicationContext context ;
  
  @PostConstruct
  public void init() {
    Map<String, DAO> ret = BeanFactoryAnnotationUtils.qualifiedBeansOfType(this.context, DAO.class, PackQualifier.class.getName()) ;
    System.out.println(ret) ;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext ;
  }
  
}
