package com.jiabin.dubbo.consumer.client.practice.controller;


import com.jiabin.dubbo.provider.api.practice.service.TestService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Controller {
    @Reference(version = "1.0",application = "")
    private TestService testService;

    @RequestMapping("/getData/{name}")
    public String getData(@PathVariable("name") String name) {
        return testService.getDate(name);
    }

}
