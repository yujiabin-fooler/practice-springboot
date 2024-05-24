package com.jiabin.sa.token.practice;

import cn.dev33.satoken.SaManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SaTokenPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaTokenPracticeApplication.class, args);
        log.info("启动成功：Sa-Token配置如下：{}", SaManager.getConfig());
    }
}
