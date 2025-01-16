package com.jiabin.guava.practice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jiabin.guava.practice.rateLimiter.RateLimiterException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({RateLimiterException.class})
  public ResponseEntity<Object> rateLimiterException(RateLimiterException e) {
    return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(e.getMessage()) ;
  }
}
