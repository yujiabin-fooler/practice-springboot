package com.jiabin.shardingjdbc.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lujunqiang
 */
@MapperScan(basePackages = {"com.jiabin.shardingjdbc.practice.dao"})
@SpringBootApplication
public class DemoShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoShardingJdbcApplication.class, args);
    }

}
