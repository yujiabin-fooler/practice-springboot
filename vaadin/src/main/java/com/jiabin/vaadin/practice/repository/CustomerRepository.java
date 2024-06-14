package com.jiabin.vaadin.practice.repository;

import com.jiabin.vaadin.practice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author liuhaihua
 * @version 1.0
 * @ClassName CustomerRepository
 * @Description todo
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
}
