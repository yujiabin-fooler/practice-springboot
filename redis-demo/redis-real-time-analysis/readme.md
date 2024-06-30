



实时分析
针对Redis作为实时分析使用场景，下面是一个Java Spring Boot应用的案例，其中使用Redis的Sorted Set来实现一个简单的用户在线时长统计和分析功能。

场景描述

假设我们正在开发一个在线教育平台，需要统计每个用户的在线时长，并根据这些数据生成实时的在线时长排行榜。

创建Spring Boot项目

使用Spring Initializr (https://start.spring.io/) 创建一个项目，并添加所需的依赖。

配置Redis连接

在src/main/resources/application.properties中配置Redis服务器的连接信息：

spring.redis.host=localhost
spring.redis.port=6379
编写业务代码

用户在线时长服务接口和实现类
@Service
public class OnlineDurationService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void updateUserOnlineDuration(String userId, long duration) {
        // 使用Sorted Set存储用户ID和在线时长
        redisTemplate.opsForZSet().incrementScore("user:online:duration", userId, duration);
    }

    public Set<String> getTopUsersByOnlineDuration(int topN) {
        // 获取在线时长最长的前N个用户
        Set<String> topUsers = redisTemplate.opsForZSet().reverseRange("user:online:duration", 0, topN - 1);
        return topUsers;
    }
}
用户登录和登出逻辑
@Controller
public class UserController {

    @Autowired
    private OnlineDurationService onlineDurationService;

    @PostMapping("/user/{userId}/login")
    public ResponseEntity<String> userLogin(@PathVariable String userId) {
        // 用户登录逻辑，可以是任何触发登录的事件
        return ResponseEntity.ok("User " + userId + " logged in");
    }

    @PostMapping("/user/{userId}/logout")
    public ResponseEntity<String> userLogout(@PathVariable String userId) {
        // 用户登出时记录在线时长
        long duration = // 计算用户在线时长的逻辑
        onlineDurationService.updateUserOnlineDuration(userId, duration);
        return ResponseEntity.ok("User " + userId + " logged out");
    }
}
获取在线时长排行榜
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
详细解释

配置Redis连接：在application.properties中配置了Redis服务器的地址和端口。

用户在线时长服务：OnlineDurationService服务中定义了两个方法，updateUserOnlineDuration用于更新用户的在线时长，这里使用Redis的Sorted Set数据结构，将用户ID作为成员，在线时长作为分数进行存储。getTopUsersByOnlineDuration方法用于获取在线时长最长的前N个用户。

用户登录和登出逻辑：在UserController中，定义了用户登录和登出的RESTful API。用户登录时，可以执行任何触发登录的逻辑；用户登出时，计算在线时长并通过OnlineDurationService服务更新到Redis中。

获取在线时长排行榜：UserRankController提供了一个GET端点/users/online-duration/top/{topN}，用于获取在线时长最长的前N个用户。

通过这种方式，我们可以利用Redis的Sorted Set来存储和排序用户的在线时长，实现一个高效的实时在线时长统计和分析功能。每当用户登出时，系统都会更新用户的在线时长，并可以快速地根据在线时长对用户进行排名，从而提供一个动态的在线时长排行榜。这对于在线教育平台等需要监控用户活跃度的业务场景非常有用。




