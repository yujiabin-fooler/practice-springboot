package com.jiabin.multidatasource.jpa.practice.repository.primary;

import com.jiabin.multidatasource.jpa.practice.entity.primary.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BookRepository
 *
 **/
public interface BookRepository extends JpaRepository<Book, Integer> {

}
