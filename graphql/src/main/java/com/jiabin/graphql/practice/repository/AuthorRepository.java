package com.jiabin.graphql.practice.repository;

import com.jiabin.graphql.practice.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Author findAuthorByBookId(Integer bookId);
}