package com.jiabin.redis.ranking.list.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/{id}/like")
    public ResponseEntity<String> likeArticle(@PathVariable String id) {
        articleService.likeArticle(id);
        return ResponseEntity.ok("点赞成功");
    }

    @GetMapping("/top/{topN}")
    public ResponseEntity<List<Article>> getTopLikedArticles(@PathVariable int topN) {
        List<Article> topArticles = articleService.getTopLikedArticles(topN);
        return ResponseEntity.ok(topArticles);
    }
}
