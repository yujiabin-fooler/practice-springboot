package com.jiabin.dynamic.router.practice.controller;

import java.lang.reflect.Method;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo.BuilderConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPatternParser;

import com.jiabin.dynamic.router.practice.handler.UserHandler;

@RestController
@RequestMapping("/routers")
public class RegistryMappingController {

  private final RequestMappingHandlerMapping requestMappingHandlerMapping ;
  public RegistryMappingController(RequestMappingHandlerMapping requestMappingHandlerMapping) {
    this.requestMappingHandlerMapping = requestMappingHandlerMapping ;
  }
  
  @GetMapping("/register")
  public ResponseEntity<String> register() throws Exception {
    BuilderConfiguration options = new BuilderConfiguration() ;
    options.setPatternParser(new PathPatternParser()) ;
    RequestMappingInfo info = RequestMappingInfo.paths("/api/user/list").methods(RequestMethod.GET).options(options).build();
    Method method = UserHandler.class.getMethod("list") ;
    this.requestMappingHandlerMapping.registerMapping(info, new UserHandler(), method);
    return ResponseEntity.ok("success") ;
  }
  
  @GetMapping("/remove")
  public ResponseEntity<String> remove() throws Exception {
    BuilderConfiguration options = new BuilderConfiguration() ;
    options.setPatternParser(new PathPatternParser()) ;
    RequestMappingInfo info = RequestMappingInfo.paths("/api/user/list").methods(RequestMethod.GET).options(options).build();
    this.requestMappingHandlerMapping.unregisterMapping(info) ;
    return ResponseEntity.ok("success") ;
  }
  
}
