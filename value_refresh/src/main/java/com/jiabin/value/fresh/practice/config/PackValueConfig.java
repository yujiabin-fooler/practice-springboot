package com.jiabin.value.fresh.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PackValueConfig {

  @Bean
  static PackValueBeanPostProcessor packValueBeanPostProcessor() {
    return new PackValueBeanPostProcessor() ;
  }
}
