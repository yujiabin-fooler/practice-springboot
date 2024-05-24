package com.jiabin.kafka.thymeleaf.practice.repository;

import com.jiabin.kafka.thymeleaf.practice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
   
}