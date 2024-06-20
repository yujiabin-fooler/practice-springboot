package com.jiabin.multidatasource.read.write.sperate.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ls-ljq
 */
@MapperScan(basePackages = {"com.jiabin.multidatasource.read.write.sperate.practice.dao"})
@SpringBootApplication(scanBasePackages = {"com.jiabin.multidatasource.read.write.sperate.practice"})
public class DemoReadWriteApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoReadWriteApplication.class, args);
    }

}
