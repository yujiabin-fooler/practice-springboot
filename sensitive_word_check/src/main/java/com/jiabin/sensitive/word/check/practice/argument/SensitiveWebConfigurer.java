package com.jiabin.sensitive.word.check.practice.argument;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class SensitiveWebConfigurer implements WebMvcConfigurer {

  private final SensitiveParamArgumentResolver argumentResolver ;
  public SensitiveWebConfigurer(SensitiveParamArgumentResolver argumentResolver) {
    this.argumentResolver = argumentResolver ;
  }
  
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(this.argumentResolver) ;
  }
}
