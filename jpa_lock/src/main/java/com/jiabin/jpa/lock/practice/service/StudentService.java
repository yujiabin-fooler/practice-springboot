package com.jiabin.jpa.lock.practice.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.hibernate.jpa.SpecHints;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.jiabin.jpa.lock.practice.domain.Student;
import com.jiabin.jpa.lock.practice.repository.StudentRepository;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PessimisticLockScope;
import jakarta.persistence.TypedQuery;

@Service
public class StudentService {

  @PersistenceContext
  private EntityManager entityManager ;
  @Resource
  private StudentRepository studentRepository ;
  @Resource
  private TransactionTemplate transactionTemplate ;
  
  @Transactional
  public Student find(Long id) {
    Student student = entityManager.find(Student.class, id, LockModeType.PESSIMISTIC_READ) ;
    return student ;
  }
  
  @Transactional
  public Student query(Long id) {
    TypedQuery<Student> query = entityManager.createQuery("from Student where id = :id", Student.class) ;
    query.setParameter("id", id);
    query.setLockMode(LockModeType.PESSIMISTIC_WRITE) ;
    return query.getSingleResult() ;
  }
  
  @Transactional
  public Student explicitLock(Long id) {
    Student student = entityManager.find(Student.class, id) ;
    
    /**
     * 如果指定了悲观锁模式类型，并且实体包含版本属性，则持久化提供者在获取数据库锁时也必须执行乐观版本检查。如果这些检查失败，将抛出OptimisticLockException。
     * 如果锁模式类型是悲观的，并且找到了实体实例但无法锁定：
     *  如果数据库锁定失败导致事务级回滚，将抛出PessimisticLockException。
     *  如果数据库锁定失败仅导致语句级回滚，将抛出LockTimeoutException。 
     */
    entityManager.lock(student, LockModeType.PESSIMISTIC_WRITE) ;
    return student ;
  }
  
  @Transactional
  public Student refresh(Long id) {
    Student student = entityManager.find(Student.class, id, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
    return student ;
  }
  
  @Transactional
  public Student refresh_1(Long id) {
    Student student = entityManager.find(Student.class, id);
    entityManager.refresh(student, LockModeType.PESSIMISTIC_FORCE_INCREMENT) ; 
    return student ;
  }
  
  @Transactional
  public Student namedQuery(Long id) {
    TypedQuery<Student> query = this.entityManager.createNamedQuery("lockStudent", Student.class) ;
    query.setParameter("id", id) ;
    return query.getSingleResult() ;
  }
  
  @Transactional
  public Student lockScope(Long id) {
    Map<String, Object> properties = new HashMap<>();
    properties.put(SpecHints.HINT_SPEC_LOCK_SCOPE, PessimisticLockScope.EXTENDED) ;
    properties.put(SpecHints.HINT_SPEC_LOCK_TIMEOUT, 2000) ;
    Student student = entityManager.find(Student.class, 1L, LockModeType.PESSIMISTIC_WRITE, properties) ;
    // System.err.println(student.getCourses()) ;
    return student ;
  }
  
  @Transactional
  public Student optimisticFind(Long id) {
    Student student = entityManager.find(Student.class, 1L, LockModeType.WRITE) ;
    try {
      student.setName("李四001");
      TimeUnit.SECONDS.sleep(5) ;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return student ;
  }
  
  @Transactional
  public Student repositoryLock(Long id) {
    return this.studentRepository.findById(id).orElse(null) ;
  }
  
  @Transactional
  public void updateStudent_0_failure() {
    int retries = 0;
    int maxRetries = 3;
    boolean success = false;
    while (!success && retries < maxRetries) {
      try {
        Student student = studentRepository.findById(1L).orElse(null) ;
        System.err.println(student) ;
        try {
          TimeUnit.SECONDS.sleep(10) ;
        } catch (InterruptedException e) {}
        if (student != null) {
          student.setName(student.getName() + " - " + new Random().nextInt(10000)) ;
          success = true ;
        }
      } catch (Exception e) { // 这里无法捕获ObjectOptimisticLockingFailureException异常
        System.err.println(e.getClass()) ;
        System.err.println(e.getMessage()) ;
        retries++;
      }
    }
    if (!success) {
      throw new RuntimeException("Failed to update product price after " + maxRetries + " retries.");
    }
  }
  
  public void updateStudent_1_ok() {
    int retries = 0;
    int maxRetries = 3;
    boolean success = false;
    while (!success && retries < maxRetries) {
      try {
        this.transactionTemplate.executeWithoutResult(status -> {
          Student student = studentRepository.findById(1L).orElse(null) ;
          System.err.println(student) ;
          try {
            TimeUnit.SECONDS.sleep(10) ;
          } catch (InterruptedException e) {}
          if (student != null) {
            student.setName(student.getName() + " - " + new Random().nextInt(10000)) ;
          }
          System.err.println("======================") ;
        }) ;
        success = true ;
      } catch(OptimisticLockingFailureException e) {
        System.err.println("发生异常: " + e.getMessage()) ;
        retries++ ;
      }
    }
  }
  
  @Transactional
  public void updateStudent() {
    int retries = 0;
    int maxRetries = 3;
    boolean success = false;
    while (!success && retries < maxRetries) {
      Student student = studentRepository.findById(1L).orElse(null) ;
      System.err.println(student) ;
      try {
        TimeUnit.SECONDS.sleep(10) ;
      } catch (InterruptedException e) {}
      if (student != null) {
        student.setName(student.getName() + " - " + new Random().nextInt(10000)) ;
        success = true ;
      }
    }
    if (!success) {
      throw new RuntimeException("Failed to update product price after " + maxRetries + " retries.");
    }
  }
  
}
