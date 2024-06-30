package com.jiabin.redis.ranking.list.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ArticleService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void likeArticle(String articleId) {
        // 增加文章的点赞数
        redisTemplate.opsForValue().increment(articleId, 1);
    }

    public List<Article> getTopLikedArticles(int topN) {
        // 获取topN个点赞数最多的文章
        Set<String> articleIds = redisTemplate.keys("article:*:likeCount");
        List<Article> topArticles = new ArrayList<>();
        for (String id : articleIds) {
            int likeCount = Integer.valueOf(redisTemplate.opsForValue().get(id));
            Article article = new Article();
            article.setId(id.replace("article:", "").replace(":likeCount", ""));
            article.setTitle("文章标题待查询");
            article.setLikeCount(likeCount);
            topArticles.add(article);
        }
        // 根据点赞数排序
        topArticles.sort((a1, a2) -> a2.getLikeCount() - a1.getLikeCount());
        return topArticles.subList(0, topN);
    }
}