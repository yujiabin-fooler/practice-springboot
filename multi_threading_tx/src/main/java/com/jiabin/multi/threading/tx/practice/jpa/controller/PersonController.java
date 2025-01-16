package com.jiabin.multi.threading.tx.practice.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.multi.threading.tx.practice.jpa.service.FuturePersonService;
import com.jiabin.multi.threading.tx.practice.jpa.service.JdbcPersonService;
import com.jiabin.multi.threading.tx.practice.jpa.service.JpaPersonService;
import com.jiabin.multi.threading.tx.practice.jpa.service.MyBatisPersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

  private final JpaPersonService jpaPersonService ;
  private final JdbcPersonService jdbcPersonService ;
  private final MyBatisPersonService myBatisPersonService ;
  private final FuturePersonService futurePersonService ;
  public PersonController(JpaPersonService personService, 
      JdbcPersonService jdbcPersonService, 
      MyBatisPersonService myBatisPersonService,
      FuturePersonService futurePersonService) {
    this.jpaPersonService = personService ;
    this.jdbcPersonService = jdbcPersonService ;
    this.myBatisPersonService = myBatisPersonService ;
    this.futurePersonService = futurePersonService ;
  }
  
  @GetMapping("/save")
  public String save() {
    this.futurePersonService.save() ;
    return "success" ;
  }
  
  // @GetMapping("/save")
  public String saveMybatis() {
    this.myBatisPersonService.save() ;
    return "success" ;
  }
  
  // @GetMapping("/save")
  public String saveJdbc() {
    this.jdbcPersonService.save() ;
    return "success" ;
  }
  
  // @GetMapping("/save")
  public String saveJpa() {
    this.jpaPersonService.save() ;
    return "success" ;
  }
}
