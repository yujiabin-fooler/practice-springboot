package com.jiabin.swagger.practice.service;

import com.jiabin.swagger.practice.dto.UserDTO;
import com.jiabin.swagger.practice.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * UserService
 *
 */
@Service
public class UserService {

    /**
     * 创建线程安全的 Map，用于模拟用户信息存储
     */
    private static final Map<Integer, UserDTO> memoryUserData = new ConcurrentHashMap<>();

    public UserDTO getUser(Integer id) {
        UserDTO userDTO = memoryUserData.get(id);
        if (userDTO == null) {
            throw new NotFoundException("User not found with id: " + id);
        }
        return userDTO;
    }

    public List<UserDTO> listUsers() {
        List<UserDTO> users = new ArrayList<>(memoryUserData.values());

        return users;
    }
    
    public void saveUser(UserDTO user) {
        memoryUserData.put(user.getId(), user);
    }
    
    public void deleteUser(Integer id) {
        Iterator<UserDTO> iterator = memoryUserData.values().iterator();

        while(iterator.hasNext()) {
            UserDTO userDTO = iterator.next();
            if (Objects.equals(id, userDTO.getId())) {
                iterator.remove();
            }
        }
    }
    

}
