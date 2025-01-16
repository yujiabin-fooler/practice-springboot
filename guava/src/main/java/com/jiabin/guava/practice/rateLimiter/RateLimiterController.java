package com.jiabin.guava.practice.rateLimiter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratelimiters")
public class RateLimiterController {

  @GetMapping("")
  @PackLimiter(key = "xxx")
  public ResponseEntity<String> get() {
    return ResponseEntity.ok("success") ;
  }
}
