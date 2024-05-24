package com.jiabin.mybatis.plus.multidatasource;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jiabin.mybatis.plus.multidatasource.mapper")
public class MybatisMultiDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisMultiDataSourceApplication.class,args);
    }
}
