package com.jiabin.hikari.practice.repository;


import com.jiabin.hikari.practice.entity.User;
import org.springframework.data.repository.CrudRepository;

public  interface UserRepository extends CrudRepository<User, String> {

}