<img src="https://minio.codestack.online/blog/202312260250328.png" alt="springboot-event" style="zoom: 25%;" />

## 什么是事件驱动

- [Spring 官方文档](https://spring.io/event-driven/)
- [AWS Event Driven](https://aws.amazon.com/cn/what-is/eda/)

> 简单来说事件驱动是一种行为型设计模式，通过建立一对多的依赖关系，使得当一个对象的状态发生变化时，所有依赖它的对象都能自动接收通知并更新。即将自身耦合的行为进行拆分，使拆分出的行为根据特定的状态变化（触发条件）自动触发。

## 事件驱动核心组件

1. **被观察者（Subject）：** 负责维护观察者列表，并在状态变化时通知观察者。被观察者可以是一个类或对象。
2. **观察者（Observer）：** 定义一个更新接口，使得在状态变化时能够接收被观察者的通知。观察者对象需要注册到被观察者上，以便接收通知。
3. **通知（Notify）：** 被观察者在状态变化时会调用观察者的更新方法，通知它们有关状态的变化。
4. **订阅（Subscribe）和取消订阅（Unsubscribe）：** 观察者可以通过订阅和取消订阅操作来注册和注销对被观察者的关注。

## 实现方式

- [x] Guava
- [x] Spring Event

### 版本依赖

- [x] JDK 17
- [x] Spring Boot 3.2.0
- [x] Guava 33.0.0-jre

![image-20231226033126185](https://minio.codestack.online/blog/202312260346012.png)

### Tips

在仅将事件拆分出事件对象与事件监听对象后，通过事件总线推送事件，仍是单线程执行，在SpringBoot中需要两个注解来实现异步执行。

- @EnableAsync 类注解，在配置类上标记开启SpringBoot异步线程
- @Async
  方法注解，表示注解的方法异步执行。`@Async 注解的方法仅在通过容器中获取的对象调用方法为异步执行，非容器对象方法调用无效`

### 导入依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
        <version>33.0.0-jre</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

## 基于Guava实现

### 创建事件对象

```java
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Guava 实现事件对象
 */
@Data
public class GuavaEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String data;

    public GuavaEvent(String data) {
        this.data = data;
    }
}
```

### 创建事件监听

```java
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
```

### 创建事件总线，并注册事件监听

```java
import com.google.common.eventbus.EventBus;
import com.jiabin.event.practice.guava.listener.GuavaEventListener;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Guava 事件总线
 */
@Configuration
public class GuavaEventBus {

    @Resource
    private GuavaEventListener guavaEventListener;

    @Bean
    public EventBus initialize() {
        EventBus eventBus = new EventBus();
        // 注册监听
        eventBus.register(guavaEventListener);
        return eventBus;
    }
}
```

### 编写Controller测试接口

```java
import com.google.common.eventbus.EventBus;
import com.jiabin.event.practice.guava.event.GuavaEvent;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/event/guava")
    public void guavaPost(@RequestParam(value = "message") String message) {
        StopWatch stopWatch = new StopWatch("guava-event");
        stopWatch.start();
        eventBus.post(new GuavaEvent(message));
        stopWatch.stop();
        log.info("guava-event Request Log: \r{}", stopWatch.prettyPrint());
    }
}
```

## 基于Spring Event实现

> Spring Boot自己维护了一个`ApplicationEventPublisher`的事件注册Bean，通过`@EventListener`注解标识监听事件。无需自己在注册一个消息总线。

#### 创建事件对象

```java
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Spring 事件对象
 */
@Data
public class SpringEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String data;

    public SpringEvent(String data) {
        this.data = data;
    }
}
```

#### 注册监听对象

```java
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
```

#### 补充测试接口

```java
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
```

## 测试

![springboot3-event](https://minio.codestack.online/blog/202312260527858.gif)