package com.jiabin.redis.real.time.analysis.practice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserRankController {

    @Autowired
    private OnlineDurationService onlineDurationService;

    @GetMapping("/online-duration/top/{topN}")
    public ResponseEntity<Set<String>> getTopUsersByOnlineDuration(@PathVariable int topN) {
        Set<String> topUsers = onlineDurationService.getTopUsersByOnlineDuration(topN);
        return ResponseEntity.ok(topUsers);
    }
}
