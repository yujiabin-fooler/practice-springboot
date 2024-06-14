package com.jiabin.validation.practice.controller;

import cn.hutool.json.JSONUtil;
import com.jiabin.validation.practice.entity.UserInfoReq;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public Map<String, Object> showHelloWorld(@RequestBody @Validated UserInfoReq req){
        Map<String, Object> map = new HashMap<>();
        map.put("msg", JSONUtil.toJsonStr(req));
        return map;
    }
}