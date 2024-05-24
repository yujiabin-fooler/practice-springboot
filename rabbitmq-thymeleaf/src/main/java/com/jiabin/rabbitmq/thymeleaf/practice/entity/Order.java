package com.jiabin.rabbitmq.thymeleaf.practice.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="orders")
@Data
public class Order {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private BigDecimal totalAmount;
    private Date orderDate;
    
    @Column(name = "is_expired")
    private boolean expired;

}