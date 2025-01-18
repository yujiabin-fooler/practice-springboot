package com.jiabin.service.annotation.practice.auth;

import org.springframework.stereotype.Component;

import com.jiabin.service.annotation.practice.AbstractAuthenticationService;
import com.jiabin.service.annotation.practice.IAuthenticationService;
import com.jiabin.service.annotation.practice.domain.User;

import jakarta.annotation.PostConstruct;

@Component
public class AuthenticationFilter {

//  private final AbstractAuthenticationService authenticationService ;
//  private final IAuthenticationService tokenAuthenticationService ;
//  private final IAuthenticationService userAuthenticationService ;
//  public AuthenticationFilter(
//      AbstractAuthenticationService authenticationService,
//      IAuthenticationService tokenAuthenticationService,
//      IAuthenticationService userAuthenticationService) {
//    this.authenticationService = authenticationService ;
//    this.tokenAuthenticationService = tokenAuthenticationService;
//    this.userAuthenticationService = userAuthenticationService;
//  }
//  
//  @PostConstruct
//  public void auth() {
//    User user = new User() ;
//    this.tokenAuthenticationService.autenticate(user) ;
//    this.userAuthenticationService.autenticate(user) ;
//    this.authenticationService.support() ;
//  }
  
//  private final IAuthenticationService authenticationService ;
//  private final AbstractAuthenticationService abstractAuthenticationService ;
//  public AuthenticationFilter(IAuthenticationService authenticationService,
//      AbstractAuthenticationService abstractAuthenticationService) {
//    this.authenticationService = authenticationService ;
//    this.abstractAuthenticationService = abstractAuthenticationService ;
//  }
//  
//  @PostConstruct
//  public void auth() {
//    User user = new User() ;
//    this.authenticationService.autenticate(user) ;
//    this.abstractAuthenticationService.support() ;
//  }
}
