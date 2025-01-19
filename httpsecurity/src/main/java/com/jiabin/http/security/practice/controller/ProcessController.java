package com.jiabin.http.security.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process")
public class ProcessController {

  @GetMapping("/ott")
  public String ott(String token) {
    System.err.printf("接收到Token: %s%n", token) ;
    return "登录成功" ;
  }
}
