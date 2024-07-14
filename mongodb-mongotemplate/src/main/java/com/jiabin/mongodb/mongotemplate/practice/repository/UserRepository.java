package com.jiabin.mongodb.mongotemplate.practice.repository;

import com.jiabin.mongodb.mongotemplate.practice.model.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Description: 用户数据持久层
 * @Author jiabin.yu
 * @Date: 2021/1/6
 */
public interface UserRepository extends MongoRepository<UserEntity, String> {

    /**
     * 根据年龄查询
     *
     * @param age
     * @return
     */
    List<UserEntity> findByAge(Integer age);


}
