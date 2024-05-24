package com.jiabin.event.practice.guava.listener;

import com.google.common.eventbus.Subscribe;
import com.jiabin.event.practice.guava.event.GuavaEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Guava 事件监听
 */
@Component
@Slf4j
public class GuavaEventListener {

    @Subscribe
    @Async
    public void onEvent(GuavaEvent event) throws InterruptedException {
        // 模拟业务耗时
        Thread.sleep(500);
        log.info("Guava - GuavaEvent onEvent : {}", event.getData());
    }
}
