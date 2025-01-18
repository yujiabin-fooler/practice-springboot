package com.jiabin.refreshscope.practice.scope;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
public class ScopeConfig {

  @Bean
  static RefreshScope refreshScope() {
    return new RefreshScope() ;
  }
  
  @Bean
  static ContextRefresher contextRefresher(ConfigurableEnvironment env, RefreshScope refreshScope) {
    return new ContextRefresher(env, refreshScope) ;
  }
  
  @Bean
  static CustomScopeConfigurer customScopeConfigurer(RefreshScope refreshScope) {
    CustomScopeConfigurer scopeConfigurer = new CustomScopeConfigurer() ;
    scopeConfigurer.addScope("refresh", refreshScope) ;
    return scopeConfigurer ;
  }
}
