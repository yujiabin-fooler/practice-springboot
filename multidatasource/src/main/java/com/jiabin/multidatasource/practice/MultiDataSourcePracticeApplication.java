package com.jiabin.multidatasource.practice;


import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
public class MultiDataSourcePracticeApplication {
    public static void main( String[] args ) {

        SpringApplication.run(MultiDataSourcePracticeApplication.class, args);

    }
}