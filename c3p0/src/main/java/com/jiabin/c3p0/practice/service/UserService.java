package com.jiabin.c3p0.practice.service;

import com.jiabin.c3p0.practice.domain.User;
import com.jiabin.c3p0.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> getUsers(){
        return repo.getData();
    }

    public void insertUser(User user){
        repo.insertData(user);
    }
}
