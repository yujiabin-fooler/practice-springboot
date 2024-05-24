package com.jiabin.c3p0.practice.controller;

import com.jiabin.c3p0.practice.domain.User;
import com.jiabin.c3p0.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UserResourceController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/user")
    public ResponseEntity<String> insertUser(@RequestBody User user){
        userService.insertUser(user);
        return ResponseEntity.ok("success!");
    }
}
