package com.jiabin.jackson.practice.long_type;

import com.jiabin.jackson.practice.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/longs")
public class LongController {

  @GetMapping("/getData")
  public Map<String, Object> getData() {
    return Map.of("code", 0, "data", 123456789012345678L) ;
  }
  
  @GetMapping("/user")
  public User getUser() {
    return new User(123456789012345678L, "Pack") ;
  }
}
