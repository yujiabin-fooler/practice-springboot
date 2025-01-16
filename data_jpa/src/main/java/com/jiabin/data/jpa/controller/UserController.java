package com.jiabin.data.jpa.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.data.jpa.domain.User;
import com.jiabin.data.jpa.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserRepository userRepository ;
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository ;
  }
  
  @GetMapping("/streamable")
  public void findAllUserStreamable() {
    this.userRepository.findAllUserStreamable().forEach(user -> {
      try {
        TimeUnit.SECONDS.sleep(100000) ;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }) ;
  }
  
  @Transactional
  @GetMapping("/stream")
  public void findAllUserStream() {
    try(Stream<User> s = this.userRepository.findAllUserStream()) {
      // s.filter(u -> u.getSex() == 0).limit(100).forEach(System.out::println) ;
      s.forEach(user -> {
        try {
          TimeUnit.SECONDS.sleep(100000) ;
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }) ;
    }
  }
  
  @GetMapping("/async")
  public CompletableFuture<List<User>> findBySex() {
    return this.userRepository.findBySex(1) ;
  }
  
  @GetMapping("/name")
  public User findByName() {
    return this.userRepository.findByName(null) ;
  }
  
//  @GetMapping("/predicate")
//  public List<User> findUserByPredicate() {
//    return this.userRepository.xxxooo(user -> {
//      return user.getAge() > 90 && user.getSex() == 1 ;
//    }) ;
//  }
}
