package com.jiabin.sa.token.practice.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.jiabin.sa.token.practice.domain.AdminUser;

/**
 * 后台用于管理Service
 * @author jiabin.yu 2020/10/15.
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取用户信息
     */
    AdminUser getAdminByUsername(String username);

    /**
     * 根据用户ID获取用户
     */
    AdminUser getAdminById(Long id);

    /**
     * 用户名密码登录
     */
    SaTokenInfo login(String username, String password);
}
