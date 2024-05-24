package com.jiabin.drools.practice.service;

import com.jiabin.drools.practice.entity.Product;
import com.jiabin.drools.practice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}