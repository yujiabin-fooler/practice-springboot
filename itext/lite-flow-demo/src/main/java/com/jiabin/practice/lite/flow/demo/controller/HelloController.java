package com.jiabin.practice.lite.flow.demo.controller;

import com.yomahub.liteflow.core.FlowExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @Autowired
    FlowExecutor flowExecutor;
    @GetMapping("/hello")
    public void hello() {
        flowExecutor.execute2Resp("orderProcessChain");
    }
}
