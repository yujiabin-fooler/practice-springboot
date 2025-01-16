package com.jiabin.dynamic.router.practice.handler;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiabin.dynamic.router.practice.domain.User;

@Component
public class UserHandler {
  
  @ResponseBody
  public User getUser(@PathVariable("id") Integer id) {
    User user = new User() ;
    user.setId(id) ;
    user.setName("张三") ;
    user.setAge(22) ;
    user.setSex("男") ;
    user.setBirthday(new Date()) ;
    return user ;
  }
  
  @ResponseBody
  public List<User> list() {
    return List.of(
          new User(2, "李四", "女", 23, new Date()),
          new User(3, "王五", "男", 33, new Date()),
          new User(4, "赵六", "女", 23, new Date())
        ) ;
  }
  
}
