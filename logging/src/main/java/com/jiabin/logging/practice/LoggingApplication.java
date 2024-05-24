package com.jiabin.logging.practice;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@Slf4j
@SpringBootApplication
public class LoggingApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoggingApplication.class, args);
        String mdcKey = "user-id";
        log.info("Logging before MDC put...");
        MDC.put(mdcKey, UUID.randomUUID().toString());
        log.info("Logging after MDC put...");
        MDC.clear();
        log.info("Logging after MDC clear...");
    }
}
