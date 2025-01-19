package com.jiabin.transaction.readonly.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.transaction.readonly.practice.domain.Person;
import com.jiabin.transaction.readonly.practice.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService ;
  public PersonController(PersonService personService) {
    this.personService = personService ;
  }
  
  @GetMapping("/{id}")
  public Person queryPerson(@PathVariable Integer id) {
    return this.personService.findById(id) ;
  }
  
}
