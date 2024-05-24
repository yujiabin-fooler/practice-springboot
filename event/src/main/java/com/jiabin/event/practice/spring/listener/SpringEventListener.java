package com.jiabin.event.practice.spring.listener;

import com.jiabin.event.practice.spring.event.SpringEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Spring event listener
 */
@Component
@Slf4j
public class SpringEventListener {
    @EventListener
    @Async
    public void onApplicationEvent(SpringEvent event) throws InterruptedException {
        // 模拟业务耗时
        Thread.sleep(500);
        log.info("SpringEvent received: {}", event.getData());
    }
}
