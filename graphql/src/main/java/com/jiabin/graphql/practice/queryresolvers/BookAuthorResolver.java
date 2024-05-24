package com.jiabin.graphql.practice.queryresolvers;


import com.jiabin.graphql.practice.model.Author;
import com.jiabin.graphql.practice.model.Book;
import com.jiabin.graphql.practice.repository.AuthorRepository;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookAuthorResolver implements GraphQLResolver<Book> {

    @Autowired
    AuthorRepository authorRepository;

    public Author getAuthor(Book book){

        return authorRepository.findAuthorByBookId(book.getId());
    }
}
