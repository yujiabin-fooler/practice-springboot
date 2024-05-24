package com.jiabin.multidatasource.jdbctemplate.practice.repository;

import com.jiabin.multidatasource.jdbctemplate.practice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * ProductRepository
 */
@Repository
public class ProductRepository {

    @Autowired
    @Qualifier("JdbcTemplateTwo")
    private JdbcTemplate jdbcTemplate;

    public Integer insertProduct(Product product) {
        String sql = "INSERT INTO product(product_name, price, address) VALUES(?,?,?)";
        int count = jdbcTemplate.update(sql, product.getProductName(), product.getPrice(), product.getAddress());
        return count;

    }

}
