package com.jiabin.multidatasource.mybatis.xml.practice.mapper.secondary;

import com.jiabin.multidatasource.mybatis.xml.practice.entity.Product;

import java.util.List;

/**
 * ProductMapper
 *
 */
public interface ProductMapper {

  List<Product> findAllProduct();

  void insertProduct(Product product);
}
