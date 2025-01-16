package com.jiabin.extension.point.practice.contextinitializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class PackApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Override
  public void initialize(ConfigurableApplicationContext context) {
//    context.addApplicationListener(null) ;
//    context.addBeanFactoryPostProcessor(null) ;
    // context.getEnvironment().getConversionService().addConverter(null) ;
    // ...
  }

}
