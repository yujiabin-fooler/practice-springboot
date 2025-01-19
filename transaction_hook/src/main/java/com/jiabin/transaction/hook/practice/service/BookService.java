package com.jiabin.transaction.hook.practice.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.jiabin.transaction.hook.practice.domain.Book;
import com.jiabin.transaction.hook.practice.event.BookCreationEvent;
import com.jiabin.transaction.hook.practice.repository.BookRepository;

@Service
public class BookService {
  
  private final Logger logger = LoggerFactory.getLogger(getClass()) ;
  
  private final BookRepository bookRepository ;
  private final AsyncCallService asyncCallService ;
  private final ApplicationContext context ;
  private final TransactionTemplate transactionTemplate ;
  public BookService(BookRepository bookRepository, 
      AsyncCallService asyncCallService,
      ApplicationContext context,
      TransactionTemplate transactionTemplate) {
    this.bookRepository = bookRepository ;
    this.asyncCallService = asyncCallService ;
    this.context = context ;
    this.transactionTemplate = transactionTemplate ;
  }
  
  @Transactional
  public void save(Book book) {
    this.context.publishEvent(new BookCreationEvent(book)) ;
    this.bookRepository.saveAndFlush(book) ;
    logger.info("保存ID为【{}】的Book完成", book.getId()) ;
  }
  
  @Transactional
  public void save_4(Book book) {
    TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
      @Override
      public void afterCompletion(int status) {
        // 判断事务正确提交
        if (status == TransactionSynchronization.STATUS_COMMITTED) {
          logger.info("当前事务正确提交...") ;
          asyncCallService.callPhone(book) ;
        }
      }
    }) ;
    this.bookRepository.saveAndFlush(book) ;
    logger.info("保存ID为【{}】的Book完成", book.getId()) ;
  }
  
  
  @Transactional
  public void save_1(Book book) {
    this.bookRepository.saveAndFlush(book) ;
    this.asyncCallService.callPhone(book) ;
    try {
      TimeUnit.MILLISECONDS.sleep(1 * 1000) ;
    } catch (InterruptedException e) {
      e.printStackTrace() ;
    }
    logger.info("保存ID为【{}】的Book完成", book.getId()) ;
    System.out.println(1 / 0) ;
  }
  
  @Transactional
  public void save_2(Book book) {
    TransactionSynchronizationManager.registerSynchronization(null);
    this.bookRepository.saveAndFlush(book) ;
    this.asyncCallService.callPhone(book) ;
    try {
      TimeUnit.MILLISECONDS.sleep(1 * 1000) ;
    } catch (InterruptedException e) {
      e.printStackTrace() ;
    }
    logger.info("保存ID为【{}】的Book完成", book.getId()) ;
  }
  
  public void save_3(Book book) {
    this.transactionTemplate.executeWithoutResult(status -> {
      try {
        this.bookRepository.saveAndFlush(book) ;
      } catch (Exception e) {
        status.setRollbackOnly() ;
      }
    });
//    System.err.println(book.getId()) ;
    this.asyncCallService.callPhone(book) ;
    try {
      TimeUnit.MILLISECONDS.sleep(1 * 1000) ;
    } catch (InterruptedException e) {
      e.printStackTrace() ;
    }
  }
  
  @Transactional
  public Book queryBook(Long id) {
    return this.bookRepository.findById(id).orElse(null) ;
  }
}
