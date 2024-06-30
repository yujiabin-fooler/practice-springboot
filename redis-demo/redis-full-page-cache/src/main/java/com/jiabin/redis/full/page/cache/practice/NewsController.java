package com.jiabin.redis.full.page.cache.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/")
    @Cacheable(value = "homePage", condition = "#root.caches[0].name == 'homePage'")
    public String homePage(Model model) {
        // 尝试从缓存中获取页面
        model.addAttribute("newsList", newsService.getNewsList());
        return "home";
    }
}