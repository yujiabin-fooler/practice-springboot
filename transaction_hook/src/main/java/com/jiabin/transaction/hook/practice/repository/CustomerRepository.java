package com.jiabin.transaction.hook.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jiabin.transaction.hook.practice.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
