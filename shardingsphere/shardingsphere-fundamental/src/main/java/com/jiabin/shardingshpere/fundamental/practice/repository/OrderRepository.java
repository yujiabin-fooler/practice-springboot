package com.jiabin.shardingshpere.fundamental.practice.repository;

import com.jiabin.shardingshpere.fundamental.practice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
