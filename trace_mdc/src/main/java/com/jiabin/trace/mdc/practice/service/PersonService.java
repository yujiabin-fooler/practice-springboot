package com.jiabin.trace.mdc.practice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.jiabin.trace.mdc.practice.domain.Person;
import com.jiabin.trace.mdc.practice.repository.PersonRepository;

@Service
public class PersonService {

  private final Logger logger = LoggerFactory.getLogger(getClass()) ;
  
  private final PersonRepository personRepository ;
  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository ;
  }
  
  public Person findById(Integer id) {
    logger.info("准备查询Id为【{}】的信息", id) ;
    return this.personRepository.findById(id).orElse(null) ;
  }
  
  @Async
  public void task() {
    logger.info("执行异步任务") ;
  }
}
