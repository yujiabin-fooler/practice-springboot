package com.jiabin.nacos.consumer.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    RestTemplate template;

    @GetMapping("/test")
    public String test() {
        return template.getForObject("http://provider-1/hello/", String.class);
    }


    @Autowired
    HelloInterface helloInterface;

    @GetMapping("/test2")
    public String test2() {
        return helloInterface.hello();
    }

}
