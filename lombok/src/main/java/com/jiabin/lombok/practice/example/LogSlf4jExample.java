package com.jiabin.lombok.practice.example;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiabin.yu 2020/12/17.
 */
@Slf4j
public class LogSlf4jExample {
    public static void main(String[] args) {
        log.info("level:{}","info");
        log.warn("level:{}","warn");
        log.error("level:{}", "error");
    }
}
