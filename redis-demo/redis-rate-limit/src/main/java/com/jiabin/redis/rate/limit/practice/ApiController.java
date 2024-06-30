package com.jiabin.redis.rate.limit.practice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @RateLimit(limit = 5, timeout = 60) // 每分钟最多5个请求
    @GetMapping("/api/resource")
    public ResponseEntity<String> getLimitedResource() {
        return ResponseEntity.ok("Access to limited resource");
    }
}
