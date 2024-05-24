package com.jiabin.kafka.thymeleaf.practice.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "kafka_orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date orderDate;
	private Long customerId;
	private BigDecimal totalAmount;
}