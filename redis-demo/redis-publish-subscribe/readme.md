



发布/订阅
针对Redis作为发布/订阅消息队列的使用场景，下面是一个Java Spring Boot应用的案例，其中使用Redis的发布/订阅功能来实现一个简单的实时消息通知系统。

场景描述

假设我们正在开发一个社交媒体平台，需要实现一个功能，当用户发布新帖子时，他/她的关注者能够实时收到通知。

环境准备

安装Java开发环境。
安装Redis并确保其运行。
创建一个Spring Boot项目，并添加以下依赖：
Spring Data Redis Reactive (对于响应式编程)
Spring WebFlux (响应式Web框架)
创建Spring Boot项目

使用Spring Initializr (https://start.spring.io/) 创建一个项目，并添加所需的依赖。

配置Redis连接

在src/main/resources/application.properties中配置Redis服务器的连接信息：

spring.redis.host=localhost
spring.redis.port=6379
编写业务代码

配置发布/订阅通道
创建一个配置类来定义发布者和订阅者使用的通道。

@Configuration
public class RedisPubSubConfig {

    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(LettuceClientConfiguration clientConfig) {
        return new LettuceConnectionFactory(clientConfig);
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(ReactiveRedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(new PostNotificationListener(), new PatternTopic("newPostChannel"));
        return container;
    }

    @Bean
    public LettuceClientConfiguration clientConfig() {
        return LettuceClientConfiguration.defaultConfiguration();
    }

    @Component
    public class PostNotificationListener implements MessageListener<String, String> {

        @Override
        public void onMessage(Message<String, String> message) {
            // 处理接收到的帖子通知
            String postContent = message.getBody();
            System.out.println("New post notification received: " + postContent);
            // 这里可以添加逻辑，比如通知关注者
        }
    }
}
发送新帖子通知
创建一个服务类来发送新帖子的通知。

@Service
public class PostService {

    @Autowired
    private ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    public Mono<Void> publishNewPostNotification(String postContent) {
        return reactiveRedisTemplate.convertAndSend("newPostChannel", postContent);
    }
}
创建控制器
创建一个控制器来处理新帖子的发布请求。

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public Mono<ResponseEntity<?>> createPost(@RequestBody String postContent) {
        return postService.publishNewPostNotification(postContent)
                         .then(Mono.just(ResponseEntity.ok().build()))
                         .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
详细解释

配置发布/订阅通道：RedisPubSubConfig配置类设置了ReactiveRedisConnectionFactory和RedisMessageListenerContainer。PostNotificationListener实现了MessageListener接口，用于接收并处理新帖子的通知。

发送新帖子通知：PostService服务中的publishNewPostNotification方法使用ReactiveRedisTemplate的convertAndSend方法将新帖子的内容发布到newPostChannel通道。

创建控制器：PostController提供了一个POST端点/posts，用于接收新帖子的内容。当接收到新帖子的请求时，它将调用PostService的publishNewPostNotification方法将新帖子的内容发送到Redis通道，并返回相应的响应。

通过这种方式，我们可以利用Redis的发布/订阅功能来实现一个高效的实时消息通知系统。每当有新帖子发布时，Redis会将通知消息发布到newPostChannel通道，所有订阅了该通道的监听器（如PostNotificationListener）都会收到消息并进行处理，比如通知帖子作者的关注者。这种模式适用于需要实时通信和通知的业务场景，如社交媒体、实时聊天应用等。


