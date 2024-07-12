package com.jiabin.activiti.practice.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author jiabin.yu   2022/3/15 22:47
 */
@Component
public class CommonUtil {

    /**
     * 当前登录用户的用户名
     */
    public String getCurrentUsername() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails != null ? userDetails.getUsername() : "";
    }


    public String uuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
