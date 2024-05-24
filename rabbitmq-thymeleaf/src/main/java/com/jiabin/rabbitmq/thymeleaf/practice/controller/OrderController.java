package com.jiabin.rabbitmq.thymeleaf.practice.controller;

import com.jiabin.rabbitmq.thymeleaf.practice.entity.Order;
import com.jiabin.rabbitmq.thymeleaf.practice.repository.OrderRepository;
import com.jiabin.rabbitmq.thymeleaf.practice.service.OrderSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderSenderService orderSenderService;

	@GetMapping("/")
	public String index(Model model) {
		List<Order> orders = orderRepository.findAll();
		model.addAttribute("orders", orders);
		return "orders/list-order";
	}

	@GetMapping("/createOrderPage")
	public String createOrderPage() {
		return "orders/create-order"; // 返回创建订单的页面
	}

	@PostMapping("/createOrder")
	public String createOrder(Order order) {
		// 创建订单并保存到数据库
		orderRepository.save(order);

		// 发送订单过期消息，设置延迟时间（示例设置为1分钟）
		orderSenderService.sendOrderExpirationMessage(order.getId(), 1 * 60 * 1000);

		return "redirect:/";
	}
}