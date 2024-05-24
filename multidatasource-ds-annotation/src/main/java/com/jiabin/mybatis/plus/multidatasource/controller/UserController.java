package com.jiabin.mybatis.plus.multidatasource.controller;

import com.jiabin.mybatis.plus.multidatasource.entity.User;
import com.jiabin.mybatis.plus.multidatasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping(value = "/query")
    public List<User> query() {
        return userService.query();
    }
}