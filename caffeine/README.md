使用Spring Boot、caffeine、Thymeleaf和Bootstrap实现用户缓存功能

以下是使用Spring Boot、caffeine、Thymeleaf和Bootstrap实现用户缓存功能的示例项目的关键代码和配置文件。首先，提供MySQL用户表DDL语句：

```sql
CREATE TABLE caffeine_users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    age INT
);
```

接下来，提供相关的pom.xml文件配置：

```xml
<dependencies>
    <!-- Spring Boot Starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.4</version>
    </dependency>

    <!-- Spring Boot Web Starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Thymeleaf -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
  
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-cache</artifactId>
  </dependency>

    <dependency>
        <groupId>com.github.ben-manes.caffeine</groupId>
        <artifactId>caffeine</artifactId>
    </dependency>

    <!-- MySQL Connector -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
</dependencies>
```

接下来，提供application.properties文件属性配置：

```properties
# 数据库连接配置
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# 开启JPA SQL日志
spring.jpa.show-sql=true

# Caffeine缓存配置
spring.cache.type=caffeine
spring.cache.caffeine.spec=maximumSize=100

# Thymeleaf配置
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.mode=HTML5

```

接下来，提供一个User实体类：

```java
package com.jiabin.caffeine.practice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="caffeine_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private int age;

}
```

然后，创建一个 UserRepository 接口：

```java
package com.jiabin.caffeine.practice.repository;

import org.springframework.data.repository.CrudRepository;

import com.jiabin.caffeine.practice.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
```

创建一个 UserService 类：

```java
package com.jiabin.caffeine.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jiabin.caffeine.practice.entity.User;
import com.jiabin.caffeine.practice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private  UserRepository userRepository;
	
	@Cacheable("users")
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}
	
	@CacheEvict(value = "users", allEntries = true)
	public void save(User user) {
		userRepository.save(user);
		
	}
	
	public User findById(  Long id) {
		 User user = userRepository.findById(id).orElse(null);
		 return user;
	}
	
	@CacheEvict(value = "users", allEntries = true)
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
}
```

接下来，创建一个 UserController 控制器类：

```java
package com.jiabin.caffeine.practice.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jiabin.caffeine.practice.UserService;
import com.jiabin.caffeine.practice.entity.User;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    @Cacheable("users")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping("/add")
    public String addUserSubmit(@ModelAttribute User user) {
    	userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "edit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/edit/{id}")
    public String editUserSubmit(@PathVariable Long id, @ModelAttribute User user) {
        user.setId(id);
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
    	userService.deleteById(id);
        return "redirect:/";
    }
}
```

缓存配置类 CacheConfig

```java
package com.jiabin.caffeine.practice.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder().maximumSize(100)); // 根据你的需求配置
        return cacheManager;
    }
}
```

创建Thymeleaf模板文件（index.html、add.html、edit.html）

用户列表页 index.html:

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户列表</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/bootstrap/5.0.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="mt-4">用户列表</h1>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>用户名</th>
                    <th>Email</th>
                    <th>年龄</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <tr th:each="user : ${users}">
                    <td th:text="${user.id}"></td>
                    <td th:text="${user.username}"></td>
                    <td th:text="${user.email}"></td>
                    <td th:text="${user.age}"></td>
                    <td>
                        <a th:href="@{'/edit/' + ${user.id}}" class="btn btn-primary btn-sm">编辑</a>
                        <a th:href="@{'/delete/' + ${user.id}}" class="btn btn-danger btn-sm">删除</a>
                    </td>
                </tr>
            </tbody>
        </table>
        <a href="/add" class="btn btn-success">添加用户</a>
    </div>
</body>
</html>
```

用户添加页 add.html:

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加用户</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/bootstrap/5.0.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="mt-4">添加用户</h1>
        <form th:action="@{/add}" method="post">
            <div class="form-group">
                <label for="username">用户名</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="age">年龄</label>
                <input type="number" class="form-control" id="age" name="age" required>
            </div>
            <button type="submit" class="btn btn-primary">添加</button>
        </form>
    </div>
</body>
</html>
```

用户编辑页 edit.html:

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>编辑用户</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/bootstrap/5.0.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="mt-4">编辑用户</h1>
        <form th:action="@{/edit/{id}}" th:object="${user}" method="post">
            <input type="hidden" th:field="*{id}">
            <div class="form-group">
                <label for="username">用户名</label>
                <input type="text" class="form-control" id="username" th:field="*{username}" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" th:field="*{email}" required>
            </div>
            <div class="form-group">
                <label for="age">年龄</label>
                <input type="number" class="form-control" id="age" th:field="*{age}" required>
            </div>
            <button type="submit" class="btn btn-primary">保存</button>
        </form>
    </div>
</body>
</html>
```

这些模板文件使用了Bootstrap的样式来美化页面，并通过Thymeleaf标签来绑定数据和处理表单提交。

在系统初始时增加10个用户数据

创建一个新的类，命名为 `DataInitializer`，并添加如下代码：

```java
package com.jiabin.caffeine.practice.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jiabin.caffeine.practice.entity.User;
import com.jiabin.caffeine.practice.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
	    if( userRepository.count() == 0 )	{
	        // 添加10个初始用户数据
	        for (int i = 1; i <= 10; i++) {
	            User user = new User();
	            user.setUsername("User" + i);
	            user.setEmail("user" + i + "@example.com");
	            user.setAge(25 + i);
	            userRepository.save(user);
	        }
	    }
    }
}
```

上述代码中，我们创建了一个实现了 `CommandLineRunner` 接口的 `DataInitializer` 类，并在 `run` 方法中添加了10个初始用户数据。

启动应用程序：

运行Spring Boot应用程序，访问http://localhost:8080/，可以显示用户列表页，查看控制台，可以看到执行的 sql 日志记录。再次刷新页面 ，控制台没有 sql 日志 ，说明数据 已经从 caffeine 读取。
