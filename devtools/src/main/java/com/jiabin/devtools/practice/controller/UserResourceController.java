package com.jiabin.devtools.practice.controller;

import com.jiabin.devtools.practice.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserResource
 *
 * @author star
 */
@RestController
@RequestMapping("/api")
public class UserResourceController {

    @GetMapping("/user")
    public ResponseEntity<User> getUser() {
        User user = new User();
        user.setName("God.Gao");
        user.setAge("21");
        user.setDesc("Hi all!");
        return ResponseEntity.ok(user);
    }
}
