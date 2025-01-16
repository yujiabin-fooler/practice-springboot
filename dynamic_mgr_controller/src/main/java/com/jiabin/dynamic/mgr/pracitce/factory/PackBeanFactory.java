package com.jiabin.dynamic.mgr.pracitce.factory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class PackBeanFactory extends DefaultListableBeanFactory {

  @Override
  public void removeSingleton(String beanName) {
    super.removeSingleton(beanName);
  }
}
