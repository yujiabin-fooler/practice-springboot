自定义Spring Boot 的 HealthIndicator ，检查数据数据库连接状态

`HealthIndicator` 是 Spring Boot Actuator 模块中的一个接口，用于定义应用程序的健康检查。它允许自定义健康检查逻辑，并将健康状况报告给 Actuator 的健康端点。以下是有关 `HealthIndicator` 的详细说明：

1. **接口定义**： `HealthIndicator` 接口是 Spring Boot Actuator 框架中的一部分，它位于 `org.springframework.boot.actuate.health` 包下。该接口定义了一个方法：

   ```java
   Health health();
   ```

   您的自定义健康指示器需要实现此方法，并返回一个 `Health` 对象，该对象表示应用程序的当前健康状态。

2. **Health 对象**： `Health` 对象表示应用程序的健康状态。它可以包括以下状态：

   - `UP`：表示应用程序健康正常。
   - `DOWN`：表示应用程序健康不正常。
   - `OUT_OF_SERVICE`：表示应用程序当前不提供服务，通常用于维护状态。
   - `UNKNOWN`：表示应用程序的健康状态无法确定。

   您可以使用 `Health` 的静态方法来创建不同状态的健康对象，例如 `Health.up()`、`Health.down()` 等。

3. **自定义健康检查逻辑**： 您可以在实现 `HealthIndicator` 接口的自定义类中编写自定义健康检查逻辑。这可以包括检查数据库连接、外部服务可用性、资源利用率等等。根据检查结果，您可以返回适当的 `Health` 对象来表示应用程序的健康状态。

4. **集成到 Actuator**： 自定义的 `HealthIndicator` 将被 Spring Boot Actuator 框架自动集成。它将定期调用 `health()` 方法，并将结果报告给 Actuator 的健康端点（通常是 `/actuator/health`）。这允许运维人员或监控系统查看应用程序的健康状态。

5. **应用程序配置**： 可以将多个自定义健康指示器添加到应用程序，以检查不同部分的健康状况。通过配置文件（如 `application.properties`）可以启用或禁用特定的健康指示器，以满足应用程序的需求。

以创建一个自定义的 Spring Boot HealthIndicator 来检查数据库连接状态。以下是一个示例：

创建一个名为 `DatabaseHealthIndicator` 的类，实现 `HealthIndicator` 接口：

```java
package com.icoderoad.example.health.conf;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHealthIndicator implements HealthIndicator {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseHealthIndicator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Health health() {
        try {
            jdbcTemplate.queryForObject("SELECT 1 FROM DUAL", Integer.class);
            return Health.up().withDetail("message", "Database is running").build();
        } catch (Exception e) {
            return Health.down().withDetail("message", "Database is not available").build();
        }
    }
}
```

在项目的 `pom.xml` 文件中，添加 JDBC 相关的依赖。例如，如果您使用 HikariCP 连接池，可以添加以下依赖：

```xml
<!-- Spring Boot Actuator -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
</dependency>
```

在 `application.properties`，配置数据库连接信息，以便应用程序能够连接到数据库

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

确保将上述配置替换为实际数据库连接信息。

现在，当 Spring Boot 应用程序运行时，`DatabaseHealthIndicator` 将定期检查数据库连接状态，将其作为应用程序的健康指示器之一，并在 Actuator 的 `/actuator/health` 端点上报告健康状况。可以使用以下端点来查看应用程序的健康状况：

```apl
/actuator/health
```

这将显示数据库连接状态，如果数据库连接可用，它将报告为 "UP"。