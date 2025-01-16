package com.jiabin.mvc.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

  @GetMapping("/index")
  public String index() {
    return "index" ;
  }
}
