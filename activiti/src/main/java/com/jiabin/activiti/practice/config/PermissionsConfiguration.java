package com.jiabin.activiti.practice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shiva   2022/2/26 12:48
 */
@Configuration
public class PermissionsConfiguration {
    private Logger logger = LoggerFactory.getLogger(PermissionsConfiguration.class);

    public static final String[][] USERS_GROUPS_AND_ROLES = {
            {"jack", "password", "user", "GROUP_activitiTeam"},
            {"rose", "password", "user", "GROUP_activitiTeam"},
            {"tom", "password", "groupManager", "GROUP_activitiTeam"},
            {"bob", "password", "projectManager", "GROUP_otherTeam"},
            {"system", "password", "companyManager"},
            {"manager", "password", "companyManager"},
            {"admin", "password", "admin"},
    };

    @Bean
    public UserDetailsService myUserDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        //这里添加用户，后面处理流程时用到的任务负责人，需要添加在这里

        for (String[] user : USERS_GROUPS_AND_ROLES) {
            List<String> authoritiesStrings = Arrays.asList(Arrays.copyOfRange(user, 2, user.length));
            logger.info("> Registering new user: " + user[0] + " with the following Authorities[" + authoritiesStrings + "]");
            inMemoryUserDetailsManager.createUser(new User(user[0], passwordEncoder().encode(user[1]),
                    authoritiesStrings.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())));
        }

        return inMemoryUserDetailsManager;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
