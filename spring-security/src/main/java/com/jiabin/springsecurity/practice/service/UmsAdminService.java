package com.jiabin.springsecurity.practice.service;


import com.jiabin.springsecurity.practice.domain.AdminUserDetails;
import com.jiabin.springsecurity.practice.domain.UmsResource;

import java.util.List;

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
     * 获取所以权限列表
     */
    List<UmsResource> getResourceList();

    /**
     * 用户名密码登录
     */
    String login(String username, String password);
}
