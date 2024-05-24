package com.jiabin.mybatisplus.practice.controller;

import com.jiabin.mybatisplus.practice.entity.User;
import com.jiabin.mybatisplus.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping(value = "/add")
    public String add(@RequestParam(value = "name") String name) {
        return userService.add(name);
    }

    @GetMapping(value = "/query")
    public List<User> query() {
        return userService.query();
    }

    @PostMapping(value = "/update")
    public String update(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name) {
        return userService.update(id, name);
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam(value = "id") int id) {
        return userService.delete(id);
    }
}
