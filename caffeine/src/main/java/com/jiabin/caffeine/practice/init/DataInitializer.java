package com.jiabin.caffeine.practice.init;

import com.jiabin.caffeine.practice.entity.User;
import com.jiabin.caffeine.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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