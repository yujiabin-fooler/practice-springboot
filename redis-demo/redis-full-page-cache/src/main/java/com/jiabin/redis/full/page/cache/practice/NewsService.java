package com.jiabin.redis.full.page.cache.practice;


import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NewsService {

    // 假设有一个方法来获取新闻列表
    public List<Article> getNewsList() {
        // 这里是获取新闻列表的逻辑
        return Collections.emptyList();
    }
}
