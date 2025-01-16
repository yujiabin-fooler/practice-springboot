package com.jiabin.data.jpa.event;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.jiabin.data.jpa.domain.Book;
import com.jiabin.data.jpa.service.AsyncCallService;

@Component
public class FollowupTaskEventConfig {

  private final AsyncCallService asyncCallService ;
  public FollowupTaskEventConfig(AsyncCallService asyncCallService) {
    this.asyncCallService = asyncCallService ;
  }
  
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void txCommited(FollowupTaskEvent event) {
    System.err.println("随访任务创建成功") ;
    Object source = event.getSource() ;
    if (source instanceof Book book) {
      this.asyncCallService.callPhone(book) ;
    }
  }
}
