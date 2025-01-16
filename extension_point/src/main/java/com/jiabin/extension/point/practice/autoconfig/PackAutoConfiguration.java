package com.jiabin.extension.point.practice.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class PackAutoConfiguration {

  @Bean
  PackCustomComponent packCustomComponent() {
    return new PackCustomComponent() ;
  }
  
}
