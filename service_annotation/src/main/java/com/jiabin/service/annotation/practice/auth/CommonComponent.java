package com.jiabin.service.annotation.practice.auth;

import org.springframework.stereotype.Component;

import com.jiabin.service.annotation.practice.IAuthenticationService;
import com.jiabin.service.annotation.practice.impl.AuthenticationComponent;

import jakarta.annotation.Resource;

@Component
public class CommonComponent {

  @Resource
  private IAuthenticationService as ;
  @Resource
  private AuthenticationComponent authenticationComponent ;
//  @Resource
//  private IAuthenticationService authenticationService ;
  
}
