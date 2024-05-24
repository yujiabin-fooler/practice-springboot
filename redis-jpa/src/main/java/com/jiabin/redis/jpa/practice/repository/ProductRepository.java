package com.jiabin.redis.jpa.practice.repository;

import com.jiabin.redis.jpa.practice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 查询秒杀商品列表
    List<Product> findByStartTimeBeforeAndEndTimeAfterAndStatus(LocalDateTime startTime, LocalDateTime endTime, int status);
    
    // 根据商品ID获取商品信息
    Product findByIdAndStatus(Long id, int status);
  
}