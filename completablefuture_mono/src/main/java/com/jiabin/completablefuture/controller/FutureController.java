package com.jiabin.completablefuture.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.completablefuture.future.FutureService;

@RestController
@RequestMapping("/futures")
public class FutureController {

  private final FutureService futureService ;
  public FutureController(FutureService futureService) {
    this.futureService = futureService ;
  }
  
  @GetMapping("/query")
  public Map<String, String> query() {
    return this.futureService.query() ;
  }
}
