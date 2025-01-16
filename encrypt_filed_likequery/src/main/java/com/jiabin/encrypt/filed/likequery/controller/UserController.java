package com.jiabin.encrypt.filed.likequery.controller;

import com.jiabin.encrypt.filed.likequery.domain.User;
import com.jiabin.encrypt.filed.likequery.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService ;
  public UserController(UserService userService) {
    this.userService = userService ;
  }
  
  @PostMapping("")
  public User save(@RequestBody User user) {
    return this.userService.save(user) ;
  }
  
  @GetMapping("/{id}")
  public User findUser(@PathVariable Long id) {
    return this.userService.findById(id) ;
  }
  
  @GetMapping("/like")
  public List<User> like(String idNo) {
    return this.userService.queryUserByIdNo(idNo) ;
  }
  
  @GetMapping("")
  public List<User> list(String idNo) {
    return this.userService.queryUsers(idNo) ;
  }
}
