package com.jiabin.encache.practice.controller;

import com.jiabin.encache.practice.dto.User;
import com.jiabin.encache.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/users/{name}")
    public ResponseEntity<User> getUser(@PathVariable("name") String name) {
        System.out.println("==================");
        User user = userService.getUser(name);
        System.out.println(cacheManager.toString());
        return ResponseEntity.ok(user);
    }
}
