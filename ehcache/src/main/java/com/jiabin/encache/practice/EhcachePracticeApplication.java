package com.jiabin.encache.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * EhcacheApplication
 *
 * @author star
 */
@SpringBootApplication
@EnableCaching
public class EhcachePracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhcachePracticeApplication.class, args);
    }
}
