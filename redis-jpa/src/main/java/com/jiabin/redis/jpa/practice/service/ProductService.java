package com.jiabin.redis.jpa.practice.service;

import com.jiabin.redis.jpa.practice.entity.Product;
import com.jiabin.redis.jpa.practice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    // 查询秒杀商品列表
    public List<Product> listSecKillProducts() {
        LocalDateTime now = LocalDateTime.now();
        return productRepository.findByStartTimeBeforeAndEndTimeAfterAndStatus(now, now, 1);
    }
    
}