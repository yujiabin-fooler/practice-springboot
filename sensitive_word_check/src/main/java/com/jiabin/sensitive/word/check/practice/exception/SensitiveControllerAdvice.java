package com.jiabin.sensitive.word.check.practice.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SensitiveControllerAdvice {

  @ExceptionHandler(SensitiveException.class)
  public ResponseEntity<Object> error(SensitiveException ex) {
    return ResponseEntity.ok(Map.of("code", -1, "message", ex.getMessage())) ;
  }
}
