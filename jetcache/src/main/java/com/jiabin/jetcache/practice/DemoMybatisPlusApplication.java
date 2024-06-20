package com.jiabin.jetcache.practice;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author junqiang.lu
 */
@EnableSwagger2
@SpringBootApplication(scanBasePackages = {"com.jiabin.jetcache.practice.*"})
@MapperScan(basePackages = {"com.jiabin.jetcache.practice.dao"})
@EnableMethodCache(basePackages = "com.ljq.demo.springboot")
public class DemoMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybatisPlusApplication.class, args);
    }

}
