package com.jiabin.aop.unifiedlog.practice.controller;


import com.jiabin.aop.unifiedlog.practice.config.PrintlnLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class OrderController {


    @PrintlnLog(description = "订单详情Controller")
    @RequestMapping("/order")
    public void getOrder(String name) {

    }
}
