




分布式锁
针对Redis作为分布式锁的使用场景，下面是一个Java Spring Boot应用的案例，其中使用Redisson作为客户端来实现分布式锁。

场景描述

假设我们有一个高流量的电子商务网站，需要执行一些资源密集型的操作，比如生成日报表。为了防止多个实例同时执行这些操作，我们需要一个分布式锁来确保每次只有一个实例可以执行这些操作。

环境准备

安装Java开发环境。
安装Redis并确保其运行。
创建一个Spring Boot项目，并添加以下依赖：
Spring Boot Starter Web
Redisson
Spring Boot Starter Cache（可选，如果需要使用Spring的缓存抽象）
创建Spring Boot项目

使用Spring Initializr (https://start.spring.io/) 创建一个项目，并添加所需的依赖。

配置Redisson连接

在src/main/resources/application.properties或application.yml中配置Redisson客户端连接到Redis服务器：

# application.properties
redisson.address=redis://localhost:6379
或者

# application.yml
redisson:
address: "redis://localhost:6379"
编写业务代码

配置Redisson
创建一个配置类来配置Redisson客户端。

@Configuration
public class RedissonConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        RedissonClientConfig config = new RedissonClientConfig();
        config.useSingleServer().setAddress(redisson.address);
        return Redisson.create(config);
    }

    @Value("${redisson.address}")
    private String redissonAddress;
}
使用分布式锁
创建一个服务类来执行需要分布式锁保护的资源密集型操作。

@Service
public class ReportService {

    @Autowired
    private RedissonClient redissonClient;

    public void generateDailyReport() {
        RLock lock = redissonClient.getLock("dailyReportLock");
        try {
            // 尝试获取锁，最多等待3秒，锁的自动过期时间设置为10秒
            if (lock.tryLock(3, 10, TimeUnit.SECONDS)) {
                // 执行生成日报表的操作
                System.out.println("Generating daily report...");
                // 模拟长时间运行的任务
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Daily report generated.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}
创建控制器
创建一个控制器来触发生成日报表的操作。

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/daily")
    public ResponseEntity<String> generateDailyReport() {
        reportService.generateDailyReport();
        return ResponseEntity.ok("Daily report generation triggered.");
    }
}
详细解释

配置Redisson连接：在配置文件中指定Redis服务器的地址，并创建一个RedissonClient Bean。

使用分布式锁：ReportService服务中的generateDailyReport方法使用Redisson的RLock来获取一个名为dailyReportLock的分布式锁。tryLock方法尝试获取锁，并指定最多等待时间和锁的自动过期时间。如果成功获取锁，则执行生成日报表的操作；如果获取锁失败，则方法将立即返回，不会执行任何操作。

创建控制器：ReportController提供了一个GET端点/reports/daily，用于触发生成日报表的操作。

通过这种方式，我们可以确保在分布式系统中，即使有多个实例运行，也只有一个实例可以执行生成日报表的操作，从而避免资源冲突和重复劳动。Redisson客户端简化了Redis分布式锁的使用，使得在Spring Boot应用中实现分布式锁变得简单而高效。


