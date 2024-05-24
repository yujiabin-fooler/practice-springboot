package com.jiabin.japidocs.practice.controller;

import com.jiabin.japidocs.practice.entity.R;
import com.jiabin.japidocs.practice.entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 用户接口类
 * @Date 2020-06-15 21:46
 */
@RequestMapping("/api/user")
@RestController
public class UserController {

    /**
     * 查询用户
     * @param age 年龄
     */
    @GetMapping("/list")
    public R<User> list(@RequestParam int age){

        User user = new User("Java旅途", 18);
        return R.ok(user);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("/add")
    public R<User> add(@RequestBody User user){

        return R.ok(user);
    }
}
