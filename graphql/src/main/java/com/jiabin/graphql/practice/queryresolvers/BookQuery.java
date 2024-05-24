package com.jiabin.graphql.practice.queryresolvers;

import com.jiabin.graphql.practice.model.Author;
import com.jiabin.graphql.practice.model.Book;
import com.jiabin.graphql.practice.repository.AuthorRepository;
import com.jiabin.graphql.practice.repository.BookRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BookQuery implements GraphQLQueryResolver{

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    public Iterable<Book> allBook(){
        return bookRepository.findAll();
    }

    public Book getBookByName(String name){
        return bookRepository.findBookByName(name);
    }

    public Iterable<Author> allAuthor(){
        return authorRepository.findAll();
    }

}
