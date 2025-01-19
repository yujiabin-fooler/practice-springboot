package com.jiabin.http.security.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sent")
public class SendController {

  @GetMapping("/ott")
  public String ott() {
    return "认证信息已经发送到你的邮件，请查看你填写的邮箱" ;
  }
}
