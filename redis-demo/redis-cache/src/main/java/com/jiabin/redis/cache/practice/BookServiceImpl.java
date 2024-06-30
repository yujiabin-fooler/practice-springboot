package com.jiabin.redis.cache.practice;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Book getBookById(String id) {

        // 尝试从Redis缓存中获取书籍
        Book cachedBook =  JSON.parseObject(redisTemplate.opsForValue().get(id),Book.class);
        if (cachedBook != null) {
            return cachedBook;
        }

        // 如果缓存中没有，从数据库查询
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            // 将查询结果放入缓存，设置过期时间为10分钟
            redisTemplate.opsForValue().set(id, JSON.toJSONString(book), 10, TimeUnit.MINUTES);
        }
        return book;
    }
}
