package com.jiabin.dubbo.consumer.practice;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Reference(version = "${demo.service.version}")
    private DubboDemoService demoService;

    @GetMapping("/test")
    public String test() {
        return demoService.helloDubbo();
    }
}
