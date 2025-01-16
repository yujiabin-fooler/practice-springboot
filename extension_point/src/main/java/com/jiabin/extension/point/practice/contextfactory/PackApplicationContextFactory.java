package com.jiabin.extension.point.practice.contextfactory;

import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

public class PackApplicationContextFactory implements ApplicationContextFactory {

  @Override
  public ConfigurableApplicationContext create(WebApplicationType webApplicationType) {
    return (webApplicationType != WebApplicationType.SERVLET) ? null : createContext();
  }

  private ConfigurableApplicationContext createContext() {
    System.err.println("使用自定义容器...") ;
    return new PackApplicationContext();
  }
  
  public static class PackApplicationContext extends AnnotationConfigServletWebServerApplicationContext{

    @Override
    protected void prepareRefresh() {
      System.err.println("准备执行容器核心方法refresh") ;
      super.prepareRefresh();
    }
  }
}
