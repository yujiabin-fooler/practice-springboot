package com.jiabin.testcontainers.practice.service;

import com.jiabin.testcontainers.practice.Repository.ProductRepository;
import com.jiabin.testcontainers.practice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public Product getProduct(String id) {
        return productRepository.findById(id).orElse(null);
    }


    public void createProduct(Product product) {
        productRepository.save(product);
    }
}