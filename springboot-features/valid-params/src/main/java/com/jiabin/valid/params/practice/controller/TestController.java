package com.jiabin.valid.params.practice.controller;

import com.jiabin.valid.params.practice.params.UserVO;
import com.jiabin.valid.params.practice.params.ValidGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/addUserInfo")
    public String addUserInfo(@Validated @RequestBody UserVO user) {
        return "插入成功!";
    }

    @PostMapping("/editUserInfo")
    public String editUserInfo(@Validated({ValidGroup.Update.class}) @RequestBody UserVO user) {
        return "编辑成功!";
    }
}
