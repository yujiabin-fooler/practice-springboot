package com.jiabin.get.endpoint.practice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.get.endpoint.practice.domain.User;

@RestController
@RequestMapping("/users")
public class UserController {

  @PostMapping("/save")
  public ResponseEntity<User> save(@RequestBody User user) {
    return ResponseEntity.ok(user) ;
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    return ResponseEntity.noContent().build() ;
  }
  
  @PutMapping("/update")
  public ResponseEntity<User> update(@RequestBody User user) {
    return ResponseEntity.ok(user) ;
  }
  
  @GetMapping("/query")
  public ResponseEntity<List<User>> query() {
    return ResponseEntity.ok(List.of(
          new User()
        )) ;
  }
  
}
