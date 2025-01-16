package com.jiabin.jpa.lock.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.jpa.lock.practice.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService ;
  public PersonController(PersonService personService) {
    this.personService = personService ;
  }
  
  @GetMapping("/lockscope/{id}")
  public Object lockscope(@PathVariable Long id) {
    return this.personService.lockScope(id) ;
  }
}
