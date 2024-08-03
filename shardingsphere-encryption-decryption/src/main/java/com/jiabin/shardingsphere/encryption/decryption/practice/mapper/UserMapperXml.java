package com.jiabin.shardingsphere.encryption.decryption.practice.mapper;

import com.jiabin.shardingsphere.encryption.decryption.practice.entity.UserEntity;

import java.util.List;

/**
 * <p>
 * UserMapperXml
 * </p>
 *
 * @author jiabin.yu
 * @since 2024/7/15
 */
public interface UserMapperXml {


    /**
     * 查询所有的信息
     * @return
     */
    List<UserEntity> findAll();

    /**
     * 新增数据
     * @param user
     */
    void insert(UserEntity user);
}

