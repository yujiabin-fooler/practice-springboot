package com.jiabin.rs.api.practice.controller;

import com.jiabin.rs.api.practice.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/users")
public class UserController {

  @GetMapping("/{id}")
  public User queryUser(@PathVariable Long id) {
    return new User(id, "姓名 - " + new Random().nextInt(100000)) ;
  }
  
  @GetMapping("/header")
  public String header(@RequestHeader("x-token") String token) {
    return token ;
  }
  
  @GetMapping("/exception")
  public User exception(Long id, String name) {
    System.out.println(1 / 0) ;
    return new User(id, name) ;
  }
  
  @GetMapping("")
  public List<User> queryUsers() throws Exception {
    TimeUnit.SECONDS.sleep(2);
    return List.of(
        new User(1L, "姓名 - " + new Random().nextInt(100000)),
        new User(2L, "姓名 - " + new Random().nextInt(100000)),
        new User(3L, "姓名 - " + new Random().nextInt(100000))) ;
  }
}
