package com.jiabin.completablefuture.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.completablefuture.mono.MonoService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/monos")
public class MonoController {

  private final MonoService monoService ;
  public MonoController(MonoService futureService) {
    this.monoService = futureService ;
  }
  
  @GetMapping("/query")
  public Mono<Map<String, String>> query() {
    long start = System.currentTimeMillis() ;
    Mono<Map<String, String>> result = this.monoService.query();
    System.err.printf("%s 耗时: %dms%n", Thread.currentThread().getName(), (System.currentTimeMillis() - start)) ;
    return result ;
  }
}
