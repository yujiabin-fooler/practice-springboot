package com.jiabin.redis.jpa.practice.service;

import com.jiabin.redis.jpa.practice.entity.Order;
import com.jiabin.redis.jpa.practice.entity.Product;
import com.jiabin.redis.jpa.practice.repository.OrderRepository;
import com.jiabin.redis.jpa.practice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class OrderService {
	
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    // 创建订单
    @Transactional
    public Order createOrder(Long userId, Long productId) {
    	Order order = orderRepository.findByUserIdAndProductId(userId, productId);
    	//检查用户是否秒杀过此商品
    	if( order!=null ) {
    		return  null;
    	}
        // 检查库存是否足够
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null || product.getStock() <= 0) {
            return null;
        }
        
        // 扣减库存
        product.setStock(product.getStock() - 1);
        productRepository.save(product);
        
        // 创建订单
        order = new Order();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setOrderTime(LocalDateTime.now());
        return orderRepository.save(order);
    }
    
}