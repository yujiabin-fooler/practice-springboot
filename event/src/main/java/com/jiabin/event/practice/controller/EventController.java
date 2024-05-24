package com.jiabin.event.practice.controller;

import com.google.common.eventbus.EventBus;
import com.jiabin.event.practice.guava.event.GuavaEvent;
import com.jiabin.event.practice.spring.event.SpringEvent;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 事件测试Controller
 */
@Slf4j
@RestController
public class EventController {

    @Resource
    private EventBus eventBus;
    @Resource
    private ApplicationEventPublisher eventPublisher;

    @GetMapping("/event/guava")
    public void guavaPost(@RequestParam(value = "message") String message) {
        StopWatch stopWatch = new StopWatch("guava-event");
        stopWatch.start();
        eventBus.post(new GuavaEvent(message));
        stopWatch.stop();
        log.info("guava-event Request Log: \r{}", stopWatch.prettyPrint());
    }

    @GetMapping("/event/spring")
    public void springPublish(@RequestParam(value = "message") String message) {
        StopWatch stopWatch = new StopWatch("spring-event");
        stopWatch.start();
        eventPublisher.publishEvent(new SpringEvent(message));
        stopWatch.stop();
        log.info("spring-event Request end: \r{}", stopWatch.prettyPrint());
    }
}
