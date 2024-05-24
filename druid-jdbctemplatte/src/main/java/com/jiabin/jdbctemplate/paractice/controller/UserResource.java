package com.jiabin.jdbctemplate.paractice.controller;

import com.jiabin.jdbctemplate.paractice.entity.User;
import com.jiabin.jdbctemplate.paractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 */
@RestController
@RequestMapping("/api")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.listUser();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/users")
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok().build();
    }
}
