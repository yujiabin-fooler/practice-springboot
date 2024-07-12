package com.jiabin.swagger.practice.service;


import com.jiabin.swagger.practice.domain.AdminUserDetails;

/**
 * 后台用于管理Service
 * @author jiabin.yu 2020/10/15.
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取用户信息
     */
    AdminUserDetails getAdminByUsername(String username);

    /**
     * 用户名密码登录
     */
    String login(String username, String password);
}
