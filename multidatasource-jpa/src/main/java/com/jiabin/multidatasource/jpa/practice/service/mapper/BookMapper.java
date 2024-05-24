package com.jiabin.multidatasource.jpa.practice.service.mapper;

import com.jiabin.multidatasource.jpa.practice.entity.primary.Book;
import com.jiabin.multidatasource.jpa.practice.service.dto.BookDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * BookMapper
 *
 */
@Service
public class BookMapper {

    public Book convertToBook(BookDTO dto) {
        Book book = new Book();
        BeanUtils.copyProperties(dto, book);

        return book;
    }

    public BookDTO convertForBook(Book book) {
        BookDTO dto = new BookDTO();
        BeanUtils.copyProperties(book, dto);

        return dto;
    }
}
