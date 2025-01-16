package com.jiabin.big.transaction.practice.service;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.jiabin.big.transaction.practice.domain.Person;
import com.jiabin.big.transaction.practice.repository.PersonRepository;

@Service
public class PersonService {

  private final DataSource dataSource ;
  private final PersonRepository personRepository ;
  public PersonService(DataSource dataSource, PersonRepository personRepository) {
    this.dataSource = dataSource ;
    this.personRepository = personRepository ;
  }
  
  @Transactional
  public void update() {
    ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource) ;
    System.err.println("PersonService: " + conHolder.getConnection().hashCode()) ;
    Person person = this.personRepository.findById(1).orElse(null) ;
    person.setAge(33) ;
    this.personRepository.saveAndFlush(person) ;
  }
}
