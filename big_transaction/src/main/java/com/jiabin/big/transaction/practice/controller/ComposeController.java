package com.jiabin.big.transaction.practice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.big.transaction.practice.domain.Person;
import com.jiabin.big.transaction.practice.service.ComposeService;

@RestController
@RequestMapping("/composes")
public class ComposeController {

  private final ComposeService composeService ;
  public ComposeController(ComposeService composeService) {
    this.composeService = composeService ;
  }
  
  @GetMapping("/save")
  public String save() {
    this.composeService.save() ;
    return "success" ;
  }
  
  @GetMapping("/batch")
  public String batch() {
    List<Person> list = new ArrayList<>() ;
    for (int i = 0; i< 50_000; i++) {
      list.add(new Person("姓名 - " + i, i)) ;
    }
    StopWatch sw = new StopWatch("BatchSave") ;
    sw.start("统计批量保存耗时") ;
    this.composeService.batchSave1(list) ;
    // this.composeService.batchSave2(list) ;
    sw.stop() ;
    System.err.println(sw.prettyPrint(TimeUnit.MILLISECONDS)) ;
    return "success" ;
  }
}
