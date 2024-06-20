package com.jiabin.elasticsearch.elasticrestemplate.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.config.EnableElasticsearchAuditing;

/**
 * @author ls-ljq
 */
@EnableElasticsearchAuditing
@SpringBootApplication(scanBasePackages = "com.jiabin.elasticsearch.elasticrestemplate.practice")
public class DemoElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoElasticsearchApplication.class, args);
    }

}
