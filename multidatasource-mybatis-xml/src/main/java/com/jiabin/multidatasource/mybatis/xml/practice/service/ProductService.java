package com.jiabin.multidatasource.mybatis.xml.practice.service;

import com.jiabin.multidatasource.mybatis.xml.practice.entity.Product;
import com.jiabin.multidatasource.mybatis.xml.practice.mapper.secondary.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProductService
 *
 */
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public List<Product> listProduct() {
        List<Product> allProduct = productMapper.findAllProduct();

        return allProduct;
    }

    public void insertProduct(Product product) {
        productMapper.insertProduct(product);
    }
}
