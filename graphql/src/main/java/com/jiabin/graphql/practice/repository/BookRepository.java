package com.jiabin.graphql.practice.repository;


import com.jiabin.graphql.practice.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Book findBookByName(String name);
}
