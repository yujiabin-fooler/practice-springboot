package com.jiabin.sensitive.word.check.practice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.sensitive.word.check.practice.annotation.Sensitive;
import com.jiabin.sensitive.word.check.practice.domain.Post;

@RestController
@RequestMapping("/posts")
public class PostController {

  @PostMapping("")
  public ResponseEntity<Object> save(@RequestBody @Validated Post post, Errors error) {
    if (error.hasErrors()) {
      List<String> err = error.getFieldErrors()
          .stream()
          .map(e -> e.getField() + ", " + e.getDefaultMessage())
          .collect(Collectors.toList()) ;
      return ResponseEntity.ok(err) ;
    }
    return ResponseEntity.ok(post) ;
  }
  
  @GetMapping
  public String param(@Sensitive(throwException = true) String title) {
    return title ;
  }
  
}
