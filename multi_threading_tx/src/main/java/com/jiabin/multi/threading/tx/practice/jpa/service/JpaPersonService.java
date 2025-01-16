package com.jiabin.multi.threading.tx.practice.jpa.service;

import java.util.concurrent.TimeUnit;

import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.jiabin.multi.threading.tx.practice.jpa.domain.Person;
import com.jiabin.multi.threading.tx.practice.jpa.repository.PersonRepository;

import jakarta.persistence.EntityManagerFactory;

@Service
public class JpaPersonService {

  private final PersonRepository personRepository ;
  private final EntityManagerFactory entityManagerFactory ;
  public JpaPersonService(PersonRepository personRepository, EntityManagerFactory entityManagerFactory) {
    this.personRepository = personRepository ;
    this.entityManagerFactory = entityManagerFactory ;
  }
  
  @Transactional
  public void save() {
    Throwable[] th = new Throwable[2] ;
    EntityManagerHolder emHolder = (EntityManagerHolder) TransactionSynchronizationManager.getResource(this.entityManagerFactory) ;
    Thread task1 = new Thread(() -> {
      TransactionSynchronizationManager.bindResource(this.entityManagerFactory, emHolder) ;
      Person person = new Person();
      person.setAge(1);
      person.setName("张三");
      try {
        TimeUnit.SECONDS.sleep(1) ;
      } catch (InterruptedException e) {}
      this.personRepository.saveAndFlush(person) ;
      System.err.printf("%s Insert Operator Result: %s%n", Thread.currentThread().getName(), person) ;
      TransactionSynchronizationManager.unbindResource(this.entityManagerFactory) ;
    }, "task-1");
    task1.setUncaughtExceptionHandler((t, e) -> {
      th[0] = e ;
    });
    
    Thread task2 = new Thread(() -> {
      TransactionSynchronizationManager.bindResource(this.entityManagerFactory, emHolder) ;
      Person person = new Person();
      person.setAge(2);
      person.setName("李四");
      this.personRepository.saveAndFlush(person) ;
      System.err.printf("%s Insert Operator Result: %s%n", Thread.currentThread().getName(), person) ;
      System.out.println(1 / 0) ;
      TransactionSynchronizationManager.unbindResource(this.entityManagerFactory) ;
    }, "task-2") ;
    task2.setUncaughtExceptionHandler((t, e) -> {
      th[1] = e ;
    });
    
    task1.start() ;
    task2.start() ;
    try {
      task1.join() ;
      task2.join() ;
    } catch (InterruptedException e) {}
    
    for (int i = 0; i < th.length; i++) {
      if (th[i] != null) {
        throw new RuntimeException(th[i]) ;
      }
    }
  }
  
}
