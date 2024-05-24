package com.jiabin.elasticsearch.thymeleaf.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiabin.elasticsearch.thymeleaf.practice.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper extends BaseMapper<Product> {
}