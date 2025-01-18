package com.jiabin.refreshscope.practice.test;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apps")
public class AppController {

  private final AppComponent appComponent ;
  private final ApplicationContext context ;
  public AppController(AppComponent appComponent, ApplicationContext context) {
    this.appComponent = appComponent ;
    this.context = context ;
  }
  
  @GetMapping("/info")
  public Object info() {
    return this.appComponent.getInfo() ;
  }
  
  @GetMapping("/bean")
  public void bean() {
    System.err.println("AppComonent: " + this.context.getBean(AppComponent.class).hashCode()) ;
  }
  
  @GetMapping("/data")
  public String data() {
    return this.context.getEnvironment().getProperty("pack.app.info") ;
  }
}
