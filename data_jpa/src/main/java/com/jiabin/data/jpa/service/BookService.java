package com.jiabin.data.jpa.service;

import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.jiabin.data.jpa.domain.Book;
import com.jiabin.data.jpa.event.FollowupTaskEvent;
import com.jiabin.data.jpa.repository.BookRepository;

@Service
public class BookService {
  
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
  
  // @Transactional
  public void save(Book book) {
    this.transactionTemplate.executeWithoutResult(status -> {
      try {
        this.bookRepository.saveAndFlush(book) ;
      } catch (Exception e) {
        status.setRollbackOnly() ;
      }
    });
    // this.context.publishEvent(new FollowupTaskEvent(book)) ;
    System.err.println(book.getId()) ;
    this.asyncCallService.callPhone(book) ;
    try {
      TimeUnit.MILLISECONDS.sleep(2 * 1000) ;
    } catch (InterruptedException e) {
      e.printStackTrace() ;
    }
  }
  
  @Transactional
  public Book queryBook(Long id) {
    return this.bookRepository.findById(id).orElse(null) ;
  }
}
