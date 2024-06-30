package com.jiabin.redis.task.scheduling.practice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskSchedulingService taskSchedulingService;

    @PostMapping("/schedule")
    public ResponseEntity<String> scheduleTask(@RequestParam long delay,
                                               @RequestParam TimeUnit timeUnit) {
        taskSchedulingService.scheduleTask(new SampleTask(), delay, timeUnit);
        return ResponseEntity.ok("Task scheduled for execution at "
                + LocalDateTime.now().plusNanos(timeUnit.toNanos(delay)));
    }
}
