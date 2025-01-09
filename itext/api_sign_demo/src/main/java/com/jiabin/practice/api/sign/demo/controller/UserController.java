package com.jiabin.practice.api.sign.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
