package com.jiabin.menu.role.practice.entity.dto;


import java.io.Serializable;

public class BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long loginUserId;

    public Long getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(Long loginUserId) {
        this.loginUserId = loginUserId;
    }
}

