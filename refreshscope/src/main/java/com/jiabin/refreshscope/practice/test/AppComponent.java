package com.jiabin.refreshscope.practice.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jiabin.refreshscope.practice.annotation.RefreshScope;

@Component
@RefreshScope
public class AppComponent {

  @Value("${pack.app.info}")
  private String info ;   
  
  public String getInfo() {
    return info ;
  }
}
