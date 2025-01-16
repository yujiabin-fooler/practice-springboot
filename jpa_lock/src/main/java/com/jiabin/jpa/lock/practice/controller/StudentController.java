package com.jiabin.jpa.lock.practice.controller;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.jpa.lock.practice.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService ;
  public StudentController(StudentService studentService) {
    this.studentService = studentService ;
  }
  
  @GetMapping("/find/{id}")
  public Object find(@PathVariable Long id) {
    return this.studentService.find(id) ;
  }
  
  @GetMapping("/query/{id}")
  public Object query(@PathVariable Long id) {
    return this.studentService.query(id) ;
  }
  
  @GetMapping("/explicitlock/{id}")
  public Object explicitlock(@PathVariable Long id) {
    return this.studentService.explicitLock(id) ;
  }
  
  @GetMapping("/refresh/{id}")
  public Object refresh(@PathVariable Long id) {
    return this.studentService.refresh(id) ;
  }
  
  @GetMapping("/namedquery/{id}")
  public Object namedquery(@PathVariable Long id) {
    return this.studentService.namedQuery(id) ;
  }
  
  @GetMapping("/lockscope/{id}")
  public Object lockscope(@PathVariable Long id) {
    return this.studentService.lockScope(id) ;
  }
  
  @GetMapping("/optimistic/{id}")
  public Object optimistic(@PathVariable Long id) {
    return this.studentService.optimisticFind(id) ;
  }
  
  @GetMapping("/repository/{id}")
  public Object repositoryLock(@PathVariable Long id) {
    return this.studentService.repositoryLock(id) ;
  }
  
  @GetMapping("/update")
  public Object update() {
    try {
      this.studentService.updateStudent() ;
    } catch (OptimisticLockingFailureException e) {
      e.printStackTrace() ;
      System.err.println(e.getMessage()) ;
    }
    return "success" ;
  }
}
