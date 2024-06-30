
限流
针对Redis作为限流功能的使用场景，下面是一个Java Spring Boot应用的案例，其中使用Redis来实现API的限流。

场景描述

假设我们正在开发一个公共API服务，该服务需要对外部请求进行限流，以防止滥用和过载。我们希望对每个IP地址每分钟的请求次数进行限制。

环境准备

安装Java开发环境。
安装Redis并确保其运行。
创建一个Spring Boot项目，并添加以下依赖：
Spring Boot Starter Web
Spring Boot Starter Data Redis
创建Spring Boot项目

使用Spring Initializr (https://start.spring.io/) 创建一个项目，并添加所需的依赖。

配置Redis连接

在src/main/resources/application.properties中配置Redis服务器的连接信息：

spring.redis.host=localhost
spring.redis.port=6379
编写业务代码

创建限流注解
定义一个自定义注解，用于标识需要限流的API。

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
int limit() default 10; // 默认每分钟请求次数限制
long timeout() default 60; // 默认时间窗口为60秒
}
创建限流拦截器
实现一个拦截器来检查请求频率。

public class RateLimiterInterceptor implements HandlerInterceptor {

    private final RedisTemplate<String, Integer> redisTemplate;
    private final String rateLimitKeyPrefix = "rate_limit:";

    public RateLimiterInterceptor(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = request.getRemoteAddr();
        String methodName = ((MethodSignature) (handler)).getMethod().getName();
        String rateLimitKey = rateLimitKeyPrefix + methodName + ":" + ip;

        int currentCount = redisTemplate.opsForValue().increment(rateLimitKey);
        if (currentCount > 1) {
            // 如果当前计数大于1，则说明请求已超过限制
            response.sendError(HttpServletResponse.SC_TOO_MANY_REQUESTS, "Too many requests, please try again later.");
            return false;
        }

        // 设置过期时间
        redisTemplate.expire(rateLimitKey, RateLimit.class.cast(((MethodSignature) handler).getMethod().getAnnotation(RateLimit.class)).timeout(), TimeUnit.SECONDS);
        return true;
    }
}
配置拦截器
配置拦截器以应用于所有控制器。

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RateLimiterInterceptor rateLimiterInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimiterInterceptor);
    }
}
应用限流注解
在需要限流的API上应用自定义的RateLimit注解。

@RestController
public class ApiController {

    @RateLimit(limit = 5, timeout = 60) // 每分钟最多5个请求
    @GetMapping("/api/resource")
    public ResponseEntity<String> getLimitedResource() {
        return ResponseEntity.ok("Access to limited resource");
    }
}
详细解释

创建限流注解：定义了一个RateLimit注解，它包含限制的请求次数和时间窗口。

创建限流拦截器：RateLimiterInterceptor实现了HandlerInterceptor接口，用于在请求处理之前检查请求频率。它使用RedisTemplate来递增每个IP地址的请求计数，并设置计数的过期时间。

配置拦截器：WebConfig类实现了WebMvcConfigurer接口，用于注册RateLimiterInterceptor拦截器，使其应用于所有的控制器方法。

应用限流注解：在ApiController中的getLimitedResource方法上应用了RateLimit注解，指定了每分钟最多5个请求的限制。

通过这种方式，我们可以利用Redis的原子递增操作和键过期特性来实现API的限流。每次请求都会检查当前IP的请求计数，如果超过限制，则返回429错误码（Too Many Requests）。这有助于保护API免受滥用，并确保服务的稳定性和可用性。


