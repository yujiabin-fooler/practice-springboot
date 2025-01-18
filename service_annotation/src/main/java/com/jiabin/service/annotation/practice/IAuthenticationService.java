package com.jiabin.service.annotation.practice;

import org.springframework.stereotype.Service;

import com.jiabin.service.annotation.practice.domain.User;

@Service
public interface IAuthenticationService {
  
  User autenticate(User user) ;
}
