package com.jiabin.transaction.hook.practice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.transaction.hook.practice.domain.Customer;
import com.jiabin.transaction.hook.practice.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerRepository customerRepository ;
  public CustomerController(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository ;
  }
  
  @GetMapping("")
  public void save() {
    List<Customer> customers = this.customerRepository.findAll();
    customers
      .stream()
      .forEach(c -> c.setOrders(
        c.getOrders().stream().filter(o -> o.getSno().equals("111")).collect(Collectors.toList())
      ));
    this.customerRepository.saveAll(customers) ;
  }
}
