package com.jiabin.multi.threading.tx.practice.jpa.service;

import com.jiabin.multi.threading.tx.practice.jpa.domain.Person;
import com.jiabin.multi.threading.tx.practice.mybatis.mapper.PersonMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionHolder;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;

@Service
public class MyBatisPersonService {

  private final SqlSessionFactory sqlSessionFactory ;
  private final PersonMapper personMapper ;
  private final DataSource dataSource ;
  public MyBatisPersonService(SqlSessionFactory sqlSessionFactory, PersonMapper personMapper, DataSource dataSource) {
    this.sqlSessionFactory = sqlSessionFactory ;
    this.personMapper = personMapper ;
    this.dataSource = dataSource ;
  }
  
  @Transactional
  public void save() {
//    Person person = new Person();
//    person.setAge(1);
//    person.setName("张三");
//    this.personMapper.save(person);
    Throwable[] th = new Throwable[2] ;
    
    ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource) ;
    
    SqlSessionUtils.getSqlSession(this.sqlSessionFactory) ;
    SqlSessionHolder sessionHolder = (SqlSessionHolder) TransactionSynchronizationManager.getResource(this.sqlSessionFactory) ;
    Thread task1 = new Thread(() -> {
      TransactionSynchronizationManager.bindResource(dataSource, conHolder) ;
      TransactionSynchronizationManager.bindResource(this.sqlSessionFactory, sessionHolder) ;
      
      Person person = new Person();
      person.setAge(1);
      person.setName("张三");
      int result = this.personMapper.save(person) ;
      System.err.printf("%s Insert Operator Result: %d 次%n", Thread.currentThread().getName(), result);
      TransactionSynchronizationManager.unbindResource(this.sqlSessionFactory) ;
    }, "task-1");
    task1.setUncaughtExceptionHandler((t, e) -> {
      th[0] = e ;
    });
    
    Thread task2 = new Thread(() -> {
      TransactionSynchronizationManager.bindResource(dataSource, conHolder) ;
      TransactionSynchronizationManager.bindResource(this.sqlSessionFactory, sessionHolder) ;
      Person person = new Person();
      person.setAge(2);
      person.setName("李四");
      int result = this.personMapper.save(person) ;
      System.err.printf("%s Insert Operator Result: %d 次%n", Thread.currentThread().getName(), result);
      System.out.println(1 / 0) ;
      TransactionSynchronizationManager.unbindResource(this.sqlSessionFactory) ;
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
