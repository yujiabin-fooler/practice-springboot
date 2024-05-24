package com.jiabin.redis.practice.controller;

import com.jiabin.redis.practice.entity.User;
import com.jiabin.redis.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * UserResource
 *
 */
@RestController
@RequestMapping("/api")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        userService.save(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{code}")
    public ResponseEntity<User> getUser(@PathVariable String code) {
        User user = userService.get(code);

        return ResponseEntity.ok(user);
    }

}
