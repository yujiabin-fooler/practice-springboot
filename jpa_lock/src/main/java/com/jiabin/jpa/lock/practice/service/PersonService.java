package com.jiabin.jpa.lock.practice.service;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.SpecHints;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiabin.jpa.lock.practice.domain.Person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PessimisticLockScope;

@Service
public class PersonService {

  @PersistenceContext
  private EntityManager entityManager ;
  
  @Transactional
  public Person lockScope(Long id) {
    Map<String, Object> properties = new HashMap<>();
    properties.put(SpecHints.HINT_SPEC_LOCK_SCOPE, PessimisticLockScope.EXTENDED) ;
    Person person = entityManager.find(Person.class, 1L, LockModeType.PESSIMISTIC_WRITE, properties) ;
    return person ;
  }
}
