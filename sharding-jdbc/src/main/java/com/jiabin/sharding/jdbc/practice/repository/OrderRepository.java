package com.jiabin.sharding.jdbc.practice.repository;

import com.jiabin.sharding.jdbc.practice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> { }
