package com.jiabin.data.jpa.service;

import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.jiabin.data.jpa.domain.Book;
import com.jiabin.data.jpa.repository.BookRepository;

@Service
public class AsyncCallService {

  private final BookRepository bookRepository ;
  public AsyncCallService(BookRepository bookRepository) {
    this.bookRepository = bookRepository ;
  }
  
  @Async
  public void callPhone(Book book) {
    System.out.println("@Async " + book.getId()) ;
    try {
      TimeUnit.MILLISECONDS.sleep(0 * 1000) ;
    } catch (InterruptedException e) {
      e.printStackTrace() ;
    }
    book.setPageCount(666) ;
    this.bookRepository.save(book) ;
  }
  
}
