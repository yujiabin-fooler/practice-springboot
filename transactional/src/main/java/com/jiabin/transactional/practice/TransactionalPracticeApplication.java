package com.jiabin.transactional.practice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class TransactionalPracticeApplication {

    public static void main( String[] args )
    {
        SpringApplication.run(TransactionalPracticeApplication.class, args);

    }
}

