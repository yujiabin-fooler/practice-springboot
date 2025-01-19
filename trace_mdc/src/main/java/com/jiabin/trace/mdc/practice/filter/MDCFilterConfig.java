package com.jiabin.trace.mdc.practice.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;

@Configuration
public class MDCFilterConfig {
  
  @Bean
  MDCInsertingServletFilter mdcInsertingServletFilter() {
    return new MDCInsertingServletFilter() ;
  }
  
}
