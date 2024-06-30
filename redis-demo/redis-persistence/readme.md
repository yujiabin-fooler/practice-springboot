




持久化
针对Redis作为任务调度使用场景，下面是一个Java Spring Boot应用的案例，其中使用Spring的@Scheduled注解与Redisson结合来实现任务调度。

场景描述

假设我们有一个自动化的营销平台，需要定期（例如每天凌晨1点）执行一些任务，比如发送时事通讯邮件给订阅用户。我们将使用Spring的定时任务功能结合Redisson来确保分布式环境下任务的准时和准确执行。

环境准备

安装Java开发环境。
安装Redis并确保其运行。
创建一个Spring Boot项目，并添加以下依赖：
Spring Boot Starter Web
Spring Boot Starter Data Redis
Redisson
创建Spring Boot项目

使用Spring Initializr (https://start.spring.io/) 创建一个项目，并添加所需的依赖。

配置Redis连接

在src/main/resources/application.properties中配置Redis服务器的连接信息：

spring.redis.host=localhost
spring.redis.port=6379
编写业务代码

创建任务执行服务
@Service
public class ScheduledTaskService {

    public void executeTask() {
        // 执行任务的逻辑，例如发送邮件
        System.out.println("Executing scheduled task: " + LocalDateTime.now());
    }
}
配置Redisson
创建一个配置类来配置Redisson客户端。

@Configuration
public class RedissonConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        RedissonClientConfig config = new RedissonClientConfig();
        config.useSingleServer().setAddress("redis://" + spring.redis.host + ":" + spring.redis.port);
        return Redisson.create(config);
    }

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;
}
创建定时任务配置
使用Redisson的RedissonScheduledExecutorService来创建一个分布式的调度器。

@Configuration
public class ScheduledConfig {

    @Bean
    public RedissonScheduledExecutorService redissonScheduledExecutorService(RedissonClient redissonClient) {
        return redissonClient.getExecutorService("myScheduler");
    }
}
创建定时任务
使用Spring的@Scheduled注解和Redisson的调度器来执行定时任务。

@Component
public class ScheduledTasks {

    @Autowired
    private ScheduledTaskService taskService;

    @Autowired
    private RedissonScheduledExecutorService scheduler;

    @Scheduled(cron = "0 0 1 * * ?") // 每天凌晨1点执行
    public void scheduledTask() {
        scheduler.schedule(() -> taskService.executeTask(), 0, TimeUnit.SECONDS);
    }
}
详细解释

配置Redis连接：在application.properties中配置了Redis服务器的地址和端口。

创建任务执行服务：ScheduledTaskService服务包含实际要执行的任务逻辑。

配置Redisson：RedissonConfig配置类设置了Redisson客户端，用于后续创建分布式调度器。

创建定时任务配置：ScheduledConfig配置类创建了一个RedissonScheduledExecutorService Bean，它将被用作分布式任务调度器。

创建定时任务：ScheduledTasks组件包含一个用@Scheduled注解的方法，该方法根据指定的cron表达式触发。当触发时，它使用Redisson的调度器来安排任务的执行。

通过这种方式，我们可以利用Spring的定时任务功能和Redisson的分布式调度器来实现任务调度。这确保了即使在分布式系统中，任务也能准时和准确地执行，避免了任务执行的冲突和重复。这对于需要定时执行的任务，如发送时事通讯、数据备份、报告生成等场景非常有用。



