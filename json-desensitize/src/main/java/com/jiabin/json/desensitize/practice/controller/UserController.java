package com.jiabin.json.desensitize.practice.controller;

import com.jiabin.json.desensitize.practice.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiabin.yu   2022-09-17 16:08
 */
@RestController
@RequestMapping("")
public class UserController {

    @GetMapping("getUser")
    public User getUser() {
        return User.builder().name("毛教员").idCard("330224193212541215").mobile("15321548565").build();
    }

    @GetMapping("listUserJson")
    public List<User> listUserJson() {
        return get();
    }

    private List<User> get() {
        //模拟下名字分别是2、3、4个字
        List<User> userList = new ArrayList<>();
        userList.add(User.builder().name("毛教员").idCard("330224193212541215").mobile("15321548565").build());
        userList.add(User.builder().name("邓太公").idCard("330265189515485621").mobile("15447589658").build());
        userList.add(User.builder().name("习大").idCard("330279152154885625").mobile("12552365448").build());
        return userList;
    }
}
