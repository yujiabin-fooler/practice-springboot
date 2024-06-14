package com.jiabin.druid.practice.controller;

import com.jiabin.druid.practice.entity.UserInfo;
import com.jiabin.druid.practice.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldController {
    @Autowired
    UserInfoMapper userInfoMapper;
    @RequestMapping("/hello")
    public  List<UserInfo> showHelloWorld(){
        List<UserInfo>  list = userInfoMapper.selectAll();
        return list;
    }
}