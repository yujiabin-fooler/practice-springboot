package com.jiabin.aop.api.limitation.practice.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jiabin.aop.api.limitation.practice.exception.PackLimitException;

@RestControllerAdvice
public class PackLimitControllerAdvice {
  
  @ExceptionHandler({PackLimitException.class})
  public ResponseEntity<Object> packLimitException(PackLimitException ex) {
    return ResponseEntity.status(500).body(ex.getMessage()) ;
  }
}
