package com.jiabin.dynamic.router.practice.controller;

import java.io.File;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jiabin.dynamic.router.practice.domain.Mapping;
import com.jiabin.dynamic.router.practice.service.RegistryMappingService;

@RestController
@RequestMapping("/registry")
public class RegistryController {
  
  private final RegistryMappingService mappingService ;
  public RegistryController(RegistryMappingService mappingService) {
    this.mappingService = mappingService ;
  }
  
  @PostMapping("")
  public ResponseEntity<String> register(@RequestBody Mapping mapping) {
    boolean ret = this.mappingService.register(mapping) ;
    return ResponseEntity.ok(ret ? "success" : "failure") ;
  }
  
  @DeleteMapping("")
  public ResponseEntity<String> unregister(@RequestBody Mapping mapping) {
    boolean ret = this.mappingService.unregister(mapping) ;
    return ResponseEntity.ok(ret ? "success" : "failure") ;
  }
  
  @PostMapping("/upload")
  public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
    File destFile = new File("e:\\router\\" + file.getOriginalFilename());
    file.transferTo(destFile) ;
    
    // 加载并注册到Spring容器中
    this.mappingService.registerBean(destFile) ;
    return ResponseEntity.ok("succes") ;
  }
}
