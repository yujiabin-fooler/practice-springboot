package com.jiabin.jetcache.practice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiabin.jetcache.practice.model.entity.UserEntity;
import com.jiabin.jetcache.practice.model.param.user.*;

/**
 * @Description: JetCache 缓存示例用户业务层接口
 * @Author jiabin.yu
 * @Date: 2023/3/25
 */
public interface IJetCacheUserService {

    /**
     * 保存(单条)
     *
     * @param userSaveParam
     * @return
     */
    UserEntity save(UserSaveParam userSaveParam);

    /**
     * 查询详情(单条)
     *
     * @param userInfoParam
     * @return
     */
    UserEntity info(UserInfoParam userInfoParam);

    /**
     * 查询列表
     *
     * @param userListParam
     * @return
     */
    IPage<UserEntity> list(UserListParam userListParam);

    /**
     * 更新(单条)
     *
     * @param userUpdateParam
     * @return
     */
    UserEntity update(UserUpdateParam userUpdateParam);

    /**
     * 删除(单条)
     *
     * @param userDeleteParam
     * @return
     */
    boolean delete(UserDeleteParam userDeleteParam);

}
