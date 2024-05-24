package com.jiabin.nacos.consumer.practice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "provider-1")
public interface HelloInterface {
    @GetMapping("/hello")
    public String hello();
}
