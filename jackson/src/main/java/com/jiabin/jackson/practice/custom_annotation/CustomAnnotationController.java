package com.jiabin.jackson.practice.custom_annotation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.jackson.domain.User;

@RestController
@RequestMapping("/customannos")
public class CustomAnnotationController {

  @GetMapping("")
  public User getUser() {
    return new User(666L, "张三", "440705197211124956", "13549995389") ;
  }
  
}
