package com.jiabin.statemachine.practice.state;

import lombok.Data;

/**
 * @description: mock orders
 */
@Data
public class Order {
    private Long orderId;
    private OrderStatusEnum orderStatus;
}
