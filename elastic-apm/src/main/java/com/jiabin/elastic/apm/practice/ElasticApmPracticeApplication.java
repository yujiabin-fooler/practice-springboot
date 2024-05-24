package com.jiabin.elastic.apm.practice;

import co.elastic.apm.attach.ElasticApmAttacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElasticApmPracticeApplication {

    public static void main(String[] args) {
        ElasticApmAttacher.attach();
        SpringApplication.run(ElasticApmPracticeApplication.class, args);
    }

}
