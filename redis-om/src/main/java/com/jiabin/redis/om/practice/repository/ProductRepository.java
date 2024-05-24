package com.jiabin.redis.om.practice.repository;

import com.jiabin.redis.om.practice.domain.Product;
import com.redis.om.spring.repository.RedisDocumentRepository;

import java.util.List;

/**
 * 商品管理Repository
 * Created by macro on 2022/3/1.
 */
public interface ProductRepository extends RedisDocumentRepository<Product, Long> {
    /**
     * 根据品牌名称查询
     */
    List<Product> findByBrandName(String brandName);

    /**
     * 根据名称或副标题搜索
     */
    List<Product> findByNameOrSubTitle(String name, String subTitle);
}
