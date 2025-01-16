package com.jiabin.data.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.data.jpa.domain.Book;
import com.jiabin.data.jpa.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

  private final BookService bookService ;
  public BookController(BookService bookService) {
    this.bookService = bookService ;
  }
  
  @GetMapping("")
  public void save() {
    Book book = new Book() ;
    book.setName("XXXOOO") ;
    book.setPageCount(223) ;
    this.bookService.save(book);
  }
  
  @GetMapping("/lock/{id}")
  public Book lock(@PathVariable Long id) {
    return this.bookService.queryBook(id) ;
  }
}
