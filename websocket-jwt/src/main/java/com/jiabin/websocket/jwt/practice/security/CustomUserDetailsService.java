package com.jiabin.websocket.jwt.practice.security;

import com.jiabin.websocket.jwt.practice.entity.User;
import com.jiabin.websocket.jwt.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // 创建 CustomUserDetails 对象并返回
        return new CustomUserDetails(user.getUsername(), user.getPassword(), Arrays.asList("admin"));
    }
}