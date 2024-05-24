package com.jiabin.multidatasource.jdbctemplate.practice.controller;

import com.jiabin.multidatasource.jdbctemplate.practice.entity.User;
import com.jiabin.multidatasource.jdbctemplate.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserResource
 *
 * @author star
 */
@RestController
@RequestMapping("/api")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        userService.insertUser(user);
        return ResponseEntity.ok().build();
    }

}
