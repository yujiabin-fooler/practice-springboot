package com.jiabin.redis.real.time.push.practice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/view")
    public ResponseEntity<String> recordProductView(@RequestParam String userId, @RequestParam String productId) {
        recommendationService.recordView(userId, productId);
        return ResponseEntity.ok("View recorded");
    }

    @GetMapping("/products")
    public ResponseEntity<List<String>> getRecommendations(@RequestParam String userId) {
        List<String> recommendedProducts = recommendationService.recommendProducts(userId);
        return ResponseEntity.ok(recommendedProducts);
    }
}
