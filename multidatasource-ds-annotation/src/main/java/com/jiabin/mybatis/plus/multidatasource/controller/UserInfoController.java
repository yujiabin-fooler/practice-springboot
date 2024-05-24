package com.jiabin.mybatis.plus.multidatasource.controller;

import com.jiabin.mybatis.plus.multidatasource.entity.UserInfo;
import com.jiabin.mybatis.plus.multidatasource.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("userinfo")
public class UserInfoController {

    @Autowired
    public UserInfoService userInfoService;

    @GetMapping(value = "/query")
    public List<UserInfo> query() {
        return userInfoService.query();
    }
}