package com.jiabin.multidatasource.jdbctemplate.practice.repository;

import com.jiabin.multidatasource.jdbctemplate.practice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 *
 */
@Repository
public class UserRepository {

    @Autowired
    @Qualifier("JdbcTemplateOne")
    private JdbcTemplate jdbcTemplate;

    public int insertUser(User user) {
        String sql = "INSERT INTO user(username, password) VALUES(?,?)";
        int count = jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
        return count;

    }
}
