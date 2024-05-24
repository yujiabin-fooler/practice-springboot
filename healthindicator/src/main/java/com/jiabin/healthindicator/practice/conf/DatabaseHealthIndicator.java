package com.jiabin.healthindicator.practice.conf;

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