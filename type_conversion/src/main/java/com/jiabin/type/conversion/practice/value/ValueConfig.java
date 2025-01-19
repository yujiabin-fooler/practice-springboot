package com.jiabin.type.conversion.practice.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jiabin.type.conversion.practice.domain.User;

@Component
public class ValueConfig {

  @Value("${pack.app.user}")
  private User user ;

  @Override
  public String toString() {
    return "ValueConfig [user=" + user + "]";
  }
}
