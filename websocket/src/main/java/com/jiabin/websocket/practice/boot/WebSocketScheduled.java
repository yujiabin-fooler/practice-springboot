package com.jiabin.websocket.practice.boot;

import com.jiabin.websocket.practice.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * WebSocket 心跳定时任务
 *
 * @author jiabin.yu  2022-07-02
 * https://github.com/dkbnull/SpringBootDemo
 */
@EnableScheduling
@Component
public class WebSocketScheduled {

    @Autowired
    private WebSocketService webSocketService;

    @Scheduled(cron = "0/15 * * * * ?")
    public void heartbeat() {
        webSocketService.heartbeat();
    }
}
