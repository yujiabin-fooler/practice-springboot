package com.jiabin.elasticsearch.query.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 应用启动类
 *
 * @author jiabin.yu 20/06/2017.
 */
// Spring Boot 应用的标识
@SpringBootApplication
public class ElasticSearchQueryPracticeApplication {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(ElasticSearchQueryPracticeApplication.class,args);
    }
}
