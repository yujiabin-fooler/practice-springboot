package com.jiabin.extension.point.practice.sourceloader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class PackIniComponent {

  @Value("${title:}")
  private String title ;
  @Value("${name:}")
  private String name ;
  @Value("${ack:}")
  private String ack ;
  @Value("${akf:}")
  private String akf ;
  @Value("${server.port:0}")
  private Integer port ;
  
  @PostConstruct
  public void init() {
    System.err.printf("title = %s, name = %s, ack = %s, akf = %s%n", title, name, ack, akf) ;
    System.err.printf("server.port1 = %d%n", port) ;
  }
  
}
