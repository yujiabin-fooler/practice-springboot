package com.jiabin.jackson.practice.jsonvalue;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.jackson.domain.User;
import com.pack.jackson.enums.PaymentStatus;

@RestController
@RequestMapping("/jsonvalue")
public class JsonValueController {

  @GetMapping("")
  public PaymentStatus status() {
    return PaymentStatus.PAID ;
  }
  
  @GetMapping("/user")
  public User user() {
    return new User(1L, "张三") ;
  }
}
