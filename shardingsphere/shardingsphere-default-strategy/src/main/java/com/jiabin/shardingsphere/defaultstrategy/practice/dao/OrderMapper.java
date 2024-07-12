package com.jiabin.shardingsphere.defaultstrategy.practice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiabin.shardingsphere.defaultstrategy.practice.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

//    @Select("SELECT o.orderId, o.orderNumber, oi.itemId, oi.productId, oi.quantity FROM t_order o" +
//            "JOIN t_order_item oi ON o.orderId = oi.orderId")
//    List<Order> selectOrderWithItems();
}