
实时推荐系统
针对Redis作为实时推荐系统存储的使用场景，下面是一个Java Spring Boot应用的案例，其中使用Redis来存储用户行为数据和偏好，以及提供一个简单的推荐功能。

场景描述

假设我们正在开发一个电子商务平台，我们希望根据用户的浏览和购买历史来推荐商品。我们将使用Redis来存储用户的这些行为数据，并根据这些数据生成推荐。

环境准备

安装Java开发环境。
安装Redis并确保其运行。
创建一个Spring Boot项目，并添加以下依赖：
Spring Boot Starter Data Redis
Spring Web
创建Spring Boot项目

使用Spring Initializr (https://start.spring.io/) 创建一个项目，并添加所需的依赖。

配置Redis连接

在src/main/resources/application.properties中配置Redis服务器的连接信息：

spring.redis.host=localhost
spring.redis.port=6379
编写业务代码

创建商品和用户实体类
public class Product {
private String id;
private String name;
// 省略构造函数、getter和setter方法
}

public class User {
private String id;
private String username;
// 省略构造函数、getter和setter方法
}
创建推荐服务
@Service
public class RecommendationService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void recordView(String userId, String productId) {
        // 记录用户查看的商品
        redisTemplate.opsForList().leftPush("user:" + userId + ":views", productId);
    }

    public List<String> recommendProducts(String userId) {
        // 简单推荐算法：返回用户查看次数最多的商品
        Set<String> viewedProducts = redisTemplate.opsForSet().members("user:" + userId + ":views");
        Map<String, Long> productViewCounts = new HashMap<>();
        viewedProducts.forEach(productId -> {
            long count = redisTemplate.opsForValue().decrement("user:" + userId + ":views:" + productId);
            productViewCounts.put(productId, count);
        });

        return productViewCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
创建控制器
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
详细解释

配置Redis连接：在application.properties中配置了Redis服务器的地址和端口。

创建商品和用户实体类：Product类代表商品信息，User类代表用户信息。

创建推荐服务：

recordView方法用于记录用户查看的商品。这里使用Redis的List来存储用户的浏览历史，每次用户查看商品时，商品ID被推入到对应用户的List中。
recommendProducts方法提供了一个简单的推荐算法。首先，获取用户的浏览历史中的所有商品ID，然后统计每个商品的浏览次数（这里简化处理，每次查看减少商品ID对应的计数器）。最后，根据浏览次数对商品进行排序，并返回用户浏览次数最多的商品列表。
创建控制器：

recordProductView端点用于记录用户查看商品的行为。
getRecommendations端点用于获取推荐商品列表。
通过这种方式，我们可以利用Redis的高性能和简单的数据结构来快速记录用户行为并生成推荐。虽然这里的推荐算法非常简单，但它展示了如何使用Redis来实现实时推荐系统的基础功能。在实际应用中，推荐算法可能会更复杂，涉及机器学习模型和更丰富的用户行为数据。



