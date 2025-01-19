package com.jiabin.type.conversion.practice.value;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.jiabin.type.conversion.practice.domain.User;

@Component
@ConfigurationProperties(prefix = "pack.app")
public class App {

  private String title ;
  private Integer sno ;
  private User user ;
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public Integer getSno() {
    return sno;
  }
  public void setSno(Integer sno) {
    this.sno = sno;
  }
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
}
