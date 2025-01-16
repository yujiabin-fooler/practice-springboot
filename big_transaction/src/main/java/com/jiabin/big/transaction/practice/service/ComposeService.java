package com.jiabin.big.transaction.practice.service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.jiabin.big.transaction.practice.domain.Person;
import com.jiabin.big.transaction.practice.repository.PersonRepository;

import jakarta.persistence.EntityManager;

@Service
public class ComposeService {

  private final PersonService personService ;
  private final UserService userService ;
  private final PersonRepository personRepository ;
  private final PlatformTransactionManager transactionManager;
  private final EntityManager em ;
  public ComposeService(PersonService personService, UserService userService, 
      PersonRepository personRepository, PlatformTransactionManager transactionManager,
      EntityManager em) {
    this.personService = personService ;
    this.userService = userService ;
    this.personRepository = personRepository ;
    this.transactionManager = transactionManager ;
    this.em = em ;
  }
  
  // @Transactional
  public void save() {
    this.personService.update(); 
    this.userService.save(); 
  }
  
  @Transactional
  public void batchSave1(List<Person> list) {
    this.personRepository.saveAll(list) ;
  }
  
  public void batchSave2(List<Person> list) {
    int batchSize = 2000 ;
    int totalItems = list.size() ;

    for (int i = 0; i < totalItems; i += batchSize) {
      int end = Math.min(i + batchSize, totalItems) ;
      List<Person> batch = list.subList(i, end) ;
      // 开始事务
      DefaultTransactionDefinition def = new DefaultTransactionDefinition() ;
      def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED) ;
      TransactionStatus status = transactionManager.getTransaction(def);
      try {
        // 保存处理后的数据
        personRepository.saveAll(batch);
        this.em.flush() ;
        // 清除持久化上下文，使所有受管实体变为游离状态。对尚未刷新到数据库的实体所做的更改将不会持久保存。
        this.em.clear() ; 
        // 提交当前批次的事务
        transactionManager.commit(status) ;
//        try{TimeUnit.SECONDS.sleep(20) ;}catch(Exception ex) {}
      } catch (Exception e) {
        // 回滚事务
        transactionManager.rollback(status) ;
        throw e ;
      }
    }
  }
}
