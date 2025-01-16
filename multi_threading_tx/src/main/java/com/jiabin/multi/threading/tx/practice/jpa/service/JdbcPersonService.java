package com.jiabin.multi.threading.tx.practice.jpa.service;

import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.jiabin.multi.threading.tx.practice.jpa.domain.Person;

@Service
public class JdbcPersonService {

  private final JdbcTemplate jdbcTemplate ;
  private final DataSource dataSource ;
  public JdbcPersonService(JdbcTemplate jdbcTemplate, DataSource dataSource) {
    this.jdbcTemplate = jdbcTemplate ;
    this.dataSource = dataSource ;
  }
  
  @Transactional
  public void save() {
    Throwable[] th = new Throwable[2] ;
    ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource) ;
    Thread task1 = new Thread(() -> {
      TransactionSynchronizationManager.bindResource(dataSource, conHolder) ;
      Person person = new Person();
      person.setAge(1);
      person.setName("张三");
      try {
        TimeUnit.SECONDS.sleep(2) ;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      int result = jdbcTemplate.update("insert into t_person (age, name) values (?, ?)", 
          person.getAge(), person.getName()) ;
      System.err.printf("%s Insert Operator Result: %d 次%n", Thread.currentThread().getName(), result);
      TransactionSynchronizationManager.unbindResource(dataSource) ;
    }, "task-1");
    task1.setUncaughtExceptionHandler((t, e) -> {
      th[0] = e ;
    });
    
    Thread task2 = new Thread(() -> {
      TransactionSynchronizationManager.bindResource(dataSource, conHolder) ;
      Person person = new Person();
      person.setAge(2);
      person.setName("李四");
      int result = jdbcTemplate.update("insert into t_person (age, name) values (?, ?)", 
          person.getAge(), person.getName()) ;
      System.err.printf("%s Insert Operator Result: %d 次%n", Thread.currentThread().getName(), result);
      //System.out.println(1 / 0) ;
      TransactionSynchronizationManager.unbindResource(dataSource) ;
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
