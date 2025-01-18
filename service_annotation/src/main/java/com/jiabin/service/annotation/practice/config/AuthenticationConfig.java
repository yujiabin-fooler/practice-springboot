package com.jiabin.service.annotation.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jiabin.service.annotation.practice.IAuthenticationService;
import com.jiabin.service.annotation.practice.auth.CommonComponent;
import com.jiabin.service.annotation.practice.impl.AuthenticationComponent;

@Configuration
public class AuthenticationConfig {

  @Bean
  IAuthenticationService authenticationService() {
    return new AuthenticationComponent() ;
  }
  
  @Bean
  CommonComponent commonComponent() {
    return new CommonComponent() ;
  }
}
