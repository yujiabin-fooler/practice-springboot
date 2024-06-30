package com.jiabin.redis.social.activities.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/social")
public class SocialController {

    @Autowired
    private SocialService socialService;

    @PostMapping("/addFriend")
    public ResponseEntity<String> addFriend(@RequestParam String userOneId, @RequestParam String userTwoId) {
        socialService.addFriend(userOneId, userTwoId);
        return ResponseEntity.ok("Friends added successfully");
    }

    @GetMapping("/friends/{userId}")
    public ResponseEntity<Set<String>> getFriends(@PathVariable String userId) {
        Set<String> friends = socialService.getFriends(userId);
        return ResponseEntity.ok(friends);
    }

    @PostMapping("/status")
    public ResponseEntity<String> postStatusUpdate(@RequestParam String userId, @RequestParam String content) {
        socialService.postStatusUpdate(userId, content);
        return ResponseEntity.ok("Status updated successfully");
    }

    @GetMapping("/timeline/{userId}")
    public ResponseEntity<List<String>> getStatusUpdates(@PathVariable String userId) {
        List<String> updates = socialService.getStatusUpdates(userId);
        return ResponseEntity.ok(updates);
    }
}
