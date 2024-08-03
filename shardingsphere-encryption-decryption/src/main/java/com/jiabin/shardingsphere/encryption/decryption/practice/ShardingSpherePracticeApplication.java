package com.jiabin.shardingsphere.encryption.decryption.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * ShardingSpherePracticeApplication
 * </p>
 *
 * @author jiabin.yu
 * @since 2024/7/15
 */
@SpringBootApplication
@MapperScan("com.jiabin.shardingsphere.encryption.decryption.practice.mapper")
public class ShardingSpherePracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSpherePracticeApplication.class, args);
    }
}
