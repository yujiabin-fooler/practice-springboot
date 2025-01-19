package com.jiabin.transaction.hook.practice.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jiabin.transaction.hook.practice.domain.Book;
import com.jiabin.transaction.hook.practice.repository.BookRepository;

@Service
public class AsyncCallService {

  private final Logger logger = LoggerFactory.getLogger(getClass()) ;
  
  private final BookRepository bookRepository ;
  public AsyncCallService(BookRepository bookRepository) {
    this.bookRepository = bookRepository ;
  }
  
  @Async
//  @Transactional(isolation = Isolation.READ_UNCOMMITTED)
  public void callPhone(Book book) {
    logger.info("准备更新ID为【{}】的Book数据", book.getId()) ;
    try {
      TimeUnit.MILLISECONDS.sleep(50) ;
    } catch (InterruptedException e) {}
    book.setPageCount(888) ;
    this.bookRepository.save(book) ;
    logger.info("ID为【{}】的Book数据，更新完成", book.getId()) ;
  }
  
}
