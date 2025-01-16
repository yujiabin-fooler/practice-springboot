package com.jiabin.data.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jiabin.data.jpa.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
