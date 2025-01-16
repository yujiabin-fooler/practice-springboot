package com.jiabin.data.jpa.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.data.jpa.domain.Customer;
import com.jiabin.data.jpa.repository.CustomerRepository;

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
