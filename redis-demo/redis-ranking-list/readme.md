



3. 排行榜和计数器
   针对Redis作为排行榜和计数器的使用场景，下面是一个Java Spring Boot应用的案例，其中使用Redis来实现一个简单的文章点赞功能，并将点赞数用作排行榜的依据。

场景描述

假设我们正在开发一个博客平台，用户可以对文章进行点赞。我们希望根据文章的点赞数来显示一个实时更新的热门文章排行榜。

创建Spring Boot项目

使用Spring Initializr (https://start.spring.io/) 创建一个项目，并添加所需的依赖。

配置Redis连接

在src/main/resources/application.properties中配置Redis 服务器的连接信息：

spring.redis.host=localhost
spring.redis.port=6379
编写业务代码

定义文章实体类
public class Article {
private String id;
private String title;
private int likeCount;
// 省略构造函数、getter和setter方法
}
创建文章服务接口和实现类
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
            int likeCount = (Integer) redisTemplate.opsForValue().get(id);
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
创建控制器
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
详细解释

配置Redis连接：在application.properties中配置了Redis服务器的地址和端口。

定义文章实体类：这是一个简单的POJO类，代表文章信息，包括文章ID、标题和点赞数。

创建文章服务：ArticleService提供了两个方法，likeArticle用于增加文章的点赞数，getTopLikedArticles用于获取点赞数最多的前N篇文章。

点赞逻辑：在likeArticle方法中，我们使用StringRedisTemplate的increment操作来原子性地增加文章的点赞数。

获取排行榜逻辑：在getTopLikedArticles方法中，我们首先获取所有文章的点赞数键，然后构建一个包含文章点赞数的列表。接着，我们对这个列表进行排序，以获取点赞数最多的前N篇文章。

创建控制器：ArticleController提供了两个RESTful API端点，/articles/{id}/like用于点赞文章，/articles/top/{topN}用于获取点赞数最多的前N篇文章。

通过这种方式，我们可以利用Redis的原子操作和高性能特性来实现一个高效的点赞和排行榜功能。每次用户点赞时，Redis都会原子性地更新点赞数，而获取排行榜时，我们可以快速地从Redis中检索和排序数据，从而提供实时的热门文章排行。


