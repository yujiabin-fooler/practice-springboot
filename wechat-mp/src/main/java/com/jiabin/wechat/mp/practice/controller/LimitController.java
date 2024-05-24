package com.jiabin.wechat.mp.practice.controller;

import com.jiabin.wechat.mp.practice.aop.RateLimit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LimitController {

    @RateLimit(key = "limitApi", limit = 2, period = 300)
    @GetMapping("/limitApi")
    @ResponseBody
    public String limitApi() {
        return "API 数据正常响应";
    }
    
    @GetMapping("/limitTest")
    public String limitTest() {
        return "limit/rate-limit";
    }
}