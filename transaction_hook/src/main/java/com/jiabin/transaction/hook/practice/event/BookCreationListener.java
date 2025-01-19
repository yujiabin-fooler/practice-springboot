package com.jiabin.transaction.hook.practice.event;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.jiabin.transaction.hook.practice.domain.Book;
import com.jiabin.transaction.hook.practice.service.AsyncCallService;

@Component
public class BookCreationListener {
  
  private final AsyncCallService asyncCallService ;
  public BookCreationListener(AsyncCallService asyncCallService) {
    this.asyncCallService = asyncCallService ;
  }

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
  public void handleCreationBook(BookCreationEvent event) {
    this.asyncCallService.callPhone((Book)event.getSource()) ;
  }
}
