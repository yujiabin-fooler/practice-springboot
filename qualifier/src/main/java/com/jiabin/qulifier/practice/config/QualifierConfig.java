package com.jiabin.qulifier.practice.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jiabin.qulifier.practice.annotation.PackQualifier;

@Configuration
public class QualifierConfig {

  @Bean
  static CustomAutowireConfigurer customAutowireConfigurer() {
    CustomAutowireConfigurer autowireConfigurer = new CustomAutowireConfigurer() ;
    autowireConfigurer.setCustomQualifierTypes(Set.of(PackQualifier.class)) ;
    return autowireConfigurer ;
  }
}
