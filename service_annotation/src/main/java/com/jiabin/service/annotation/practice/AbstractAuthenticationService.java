package com.jiabin.service.annotation.practice;

import org.springframework.stereotype.Service;

@Service
public abstract class AbstractAuthenticationService {

  public boolean support() {
    return false ;
  }
}
