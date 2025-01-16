package com.jiabin.dynamic.mgr.pracitce.advisor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dynamic")
public class DynamicRegisterController {

  private final DynamicRegisterComponent dynamicRegisterComponent ;
  public DynamicRegisterController(DynamicRegisterComponent dynamicRegisterComponent) {
    this.dynamicRegisterComponent = dynamicRegisterComponent ;
  }
  
  @GetMapping("/register/{className}")
  public ResponseEntity<Object> register(@PathVariable String className) {
    Class<?> clazz = null ;
    try {
      clazz = Class.forName(className) ;
    } catch (ClassNotFoundException e) {
      return ResponseEntity.ok(e.getMessage()) ;
    }
    this.dynamicRegisterComponent.register(clazz) ;
    return ResponseEntity.ok("成功修改") ;
  }
  
  @GetMapping("/restore/{className}")
  public ResponseEntity<Object> restore(@PathVariable String className) {
    Class<?> clazz = null ;
    try {
      clazz = Class.forName(className) ;
    } catch (ClassNotFoundException e) {
      return ResponseEntity.ok(e.getMessage()) ;
    }
    this.dynamicRegisterComponent.restore(clazz, "logAdvisor") ;
    return ResponseEntity.ok("成功修改") ;
  }
}
