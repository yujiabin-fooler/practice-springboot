


2. 会话存储
   针对Redis作为会话存储的使用场景，下面是一个Java Spring Boot应用的案例，其中使用Redis来存储用户会话信息。

场景描述

假设我们正在开发一个Web应用程序，用户需要登录以访问某些受保护的资源。为了管理用户会话，我们可以使用Redis来存储会话信息，而不是将它们存储在服务器的内存中或数据库中。

创建Spring Boot项目

使用Spring Initializr (https://start.spring.io/) 创建一个项目，并添加所需的依赖。

配置Redis连接

在src/main/resources/application.properties中配置Redis服务器的连接信息：

spring.redis.host=localhost
spring.redis.port=6379
server.servlet.session.persistent=false
server.servlet.session.timeout=15
spring.session.store-type=redis
编写业务代码

创建用户实体类
public class User {
private String username;
private String password;
// 省略构造函数、getter和setter方法
}
创建用户存储库接口
这里我们使用内存存储作为示例，实际应用中应连接到数据库。

@Service
public class InMemoryUserRepository implements UserDetailsService {

    private static final Map<String, User> users = new HashMap<>();

    static {
        users.put("user", new User("user", "password"));
        // 添加更多用户...
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
配置Spring Security
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/", "/home", "/register").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
            .and()
            .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
创建登录和登出控制器
@Controller
public class WebController {

    @GetMapping("/login"
    public String login() {
        return "login";
    }

    @GetMapping("/admin"
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/user"
    public String userPage() {
        return "user";
    }

    @PostMapping("/logout"
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login?logout";
    }
}
创建登录页面（login.html）
这是一个简单的HTML页面，包含表单用于用户登录。

详细解释

配置Redis连接：在application.properties中配置了Redis服务器的地址和端口，并设置了Spring Session的存储类型为Redis。

创建用户实体类：这是一个简单的POJO类，代表用户信息。

用户存储库接口：InMemoryUserRepository实现了UserDetailsService接口，用于Spring Security的认证过程。实际应用中，应连接到数据库来获取用户信息。

配置Spring Security：WebSecurityConfig类配置了Spring Security的认证和授权规则。它还配置了自定义的登录页面和登出逻辑。

创建控制器：WebController提供了访问不同页面的路由和登出逻辑。

登录页面：login.html是用户登录的前端页面。

通过这种方式，Spring Session会自动管理用户的会话信息，并将其存储在Redis中。当用户登录时，会话信息将被存储在Redis中，而不是传统的服务器端HttpSession中。这使得会话信息可以在多个服务器实例之间共享，非常适合于分布式部署的Web应用程序。此外，Redis的高性能也有助于提高应用程序的响应速度。
