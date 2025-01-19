package com.jiabin.transaction.readonly.practice.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jiabin.transaction.readonly.practice.domain.Person;
import com.jiabin.transaction.readonly.practice.repository.PersonRepository;

import jakarta.persistence.EntityManager;

@Service
public class PersonService {

  private final Logger logger = LoggerFactory.getLogger(getClass()) ;
  
  private final PersonRepository personRepository ;
  private final JdbcClient jdbcClient;
  private final EntityManager entityManager ;
  public PersonService(PersonRepository personRepository, JdbcClient jdbcClient, EntityManager entityManager) {
    this.personRepository = personRepository ;
    this.jdbcClient = jdbcClient ;
    this.entityManager = entityManager ;
  }
  
  @Transactional(readOnly = true)
  public Person findById(Integer id) {
    Person person = null ; 
    person = this.personRepository.queryById(id) ;
    // 修改操作
    // person.setAge(33) ;
    return person ;
  }
  
  @Transactional(readOnly = true)
  public Person findById3(Integer id) {
    Person person = null ; 
    person = this.personRepository.queryById(id) ;
    // 修改操作
    person.setAge(33) ;
    this.personRepository.save(person) ;
//    int ret = this.jdbcClient.sql("update t_person set age = 33 where id = 1").update() ;
//    System.err.printf("修改结果: %d%n", ret) ;
    return person ;
  }
  
  @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
  public Person findById2(Integer id) {
//    logger.info("准备查询Id为【{}】的信息", id) ;
    Person person = null ; 
    // person = this.personRepository.queryById(id) ;
    // 否则将从一级缓存中获取（线程上下文中获取的是同一个EntityManager）
    this.entityManager.clear() ; 
    person = this.jdbcClient.sql("select * from t_person where id = ?").param(1, id).query(Person.class).single() ;
    System.err.printf("第一次查询: %s%n", person) ;
    try {TimeUnit.SECONDS.sleep(10) ;} catch (InterruptedException e) {}
    // person = this.personRepository.queryById(id) ;
    person = this.jdbcClient.sql("select * from t_person where id = ?").param(1, id).query(Person.class).single() ;
    System.err.printf("第二次查询: %s%n", person) ;
    return person ;
  }
  
  // 报错：java.sql.SQLException: Cannot execute statement in a READ ONLY transaction.
  @Transactional(readOnly = true)
  public Person findById6(Integer id) {
    Person person = null ; 
    person = this.jdbcClient.sql("select * from t_person where id = ? FOR UPDATE").param(1, id).query(Person.class).single() ;
    return person ;
  }
  
  @Transactional(readOnly = true)
  public Person findById5(Integer id) {
    Person person = null ; 
    person = this.jdbcClient.sql("select * from t_person where id = ?").param(1, id).query(Person.class).single() ;
    return person ;
  }
  
  @Transactional(readOnly = true)
  public Person findById0(Integer id) {
//    logger.info("准备查询Id为【{}】的信息", id) ;
    Person person = null ; 
    person = this.personRepository.queryById(id) ;
    person.setName("中国") ;
    return person ;
  }
}
