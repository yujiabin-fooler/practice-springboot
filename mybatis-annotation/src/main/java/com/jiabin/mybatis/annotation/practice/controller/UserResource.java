package com.jiabin.mybatis.annotation.practice.controller;

import com.jiabin.mybatis.annotation.practice.entity.User;
import com.jiabin.mybatis.annotation.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * UserResource
 *
 **/
@RestController
@RequestMapping("/api")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<User> getUser(@RequestParam  String name) {
        User user = userService.getUser(name);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        userService.insertUser(user.getUsername(), user.getPassword());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/users")
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        userService.updateUser(user);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);

        return ResponseEntity.ok().build();
    }
}
