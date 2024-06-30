package com.jiabin.redis.time.series.data.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/monitoring")
public class MonitoringController {

    @Autowired
    private MonitoringService monitoringService;

    @PostMapping("/logCpuUsage")
    public ResponseEntity<String> logCpuUsage(@RequestParam String serverId, @RequestParam double cpuUsage) {
        monitoringService.logCpuUsage(serverId, cpuUsage);
        return ResponseEntity.ok("CPU usage logged");
    }

    @GetMapping("/cpuUsageHistory")
    public ResponseEntity<List<CpuUsageData>> getCpuUsageHistory(@RequestParam String serverId,
                                                                 @RequestParam Instant start,
                                                                 @RequestParam Instant end) {
        List<CpuUsageData> history = monitoringService.getCpuUsageHistory(serverId, start, end);
        return ResponseEntity.ok(history);
    }
}
