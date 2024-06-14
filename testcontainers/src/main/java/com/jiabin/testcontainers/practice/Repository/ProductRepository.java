package com.jiabin.testcontainers.practice.Repository;

import com.jiabin.testcontainers.practice.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
}