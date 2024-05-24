package com.jiabin.wechat.mp.practice.controller;

import com.jiabin.wechat.mp.practice.entity.CacheUser;
import com.jiabin.wechat.mp.practice.service.CacheUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class CacheUserController {

    @Autowired
    private CacheUserService userService;

    @PostMapping("/get")
    public ResponseEntity<CacheUser> getUser(@RequestBody CacheUser user) {
    	CacheUser createdUser = userService.getUserById(user.getId());
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("update/{userId}")
    public ResponseEntity<CacheUser> updateUser(@PathVariable Long userId, @RequestBody CacheUser user) {
    	CacheUser updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("del/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}