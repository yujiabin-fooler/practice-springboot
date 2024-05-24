package com.jiabin.rabbitmq.thymeleaf.practice.repository;

import com.jiabin.rabbitmq.thymeleaf.practice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}