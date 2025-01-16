package com.jiabin.aop.authority.practice.controller;

import com.jiabin.aop.authority.practice.interceptor.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

  @PreAuthorize("'api.save'")
  @GetMapping("/save")
  public Object save() {
    return "save" ;
  }
  
  @PreAuthorize("'api.update'")
  @GetMapping("/update")
  public Object update() {
    return "update" ;
  }
  
  @PreAuthorize("#id != 1")
  @GetMapping("/{id}")
  public Object query(@PathVariable Long id) {
    return "query" ;
  }
  
  @PreAuthorize("username eq 'admin'")
  @GetMapping("/delete")
  public Object delete() {
    return "delete" ;
  }
}
