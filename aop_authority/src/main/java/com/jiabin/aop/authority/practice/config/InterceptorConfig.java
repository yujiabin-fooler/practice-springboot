package com.jiabin.aop.authority.practice.config;

import com.jiabin.aop.authority.practice.interceptor.AuthInterceptor;
import com.jiabin.aop.authority.practice.interceptor.TokenInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorConfig implements WebMvcConfigurer {

  private final TokenInterceptor tokenInterceptor ;
  private final AuthInterceptor authInterceptor ;
  public InterceptorConfig(TokenInterceptor tokenInterceptor, AuthInterceptor authInterceptor) {
    this.tokenInterceptor = tokenInterceptor ;
    this.authInterceptor = authInterceptor ;
  }
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(tokenInterceptor).order(-2).addPathPatterns("/api/**") ;
    registry.addInterceptor(authInterceptor).order(-1).addPathPatterns("/api/**") ;
  }
}
