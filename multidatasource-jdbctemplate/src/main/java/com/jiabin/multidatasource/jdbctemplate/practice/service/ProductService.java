package com.jiabin.multidatasource.jdbctemplate.practice.service;

import com.jiabin.multidatasource.jdbctemplate.practice.entity.Product;
import com.jiabin.multidatasource.jdbctemplate.practice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductService
 *
 */
@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public void insertProduct(Product product) {
        productRepository.insertProduct(product);
        logger.info("Insert product success");
    }
}
