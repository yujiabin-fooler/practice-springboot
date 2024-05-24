package com.jiabin.elasticsearch.thymeleaf.practice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiabin.elasticsearch.thymeleaf.practice.entity.Product;

import java.util.List;

public interface ProductService extends IService<Product> {
	
	 public void importProductsToElasticsearch();
	 
	 public List<Product> searchProducts(String keyword);
	 
}