package com.jiabin.redis.om.practice;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRedisDocumentRepositories(basePackages = "com.macro.mall.tiny.*")
public class RedisOMPraticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisOMPraticeApplication.class, args);
    }

}
