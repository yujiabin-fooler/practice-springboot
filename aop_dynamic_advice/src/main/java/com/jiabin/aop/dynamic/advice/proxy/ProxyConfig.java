package com.jiabin.aop.dynamic.advice.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyConfig {
  
  @Bean
  static PackAdvisorAutoProxyCreator packAdvisorAutoProxyCreator() {
    return new PackAdvisorAutoProxyCreator() ;
  }
}
