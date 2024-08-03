package com.jiabin.menu.role.practice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author jiabin.yu
 * @since 2020-06-28
 */

@TableName("tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息给过来的ID
     */
    @TableId("id")
    private Long id;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 姓名
     */
    @TableField("user_name")
    private String name;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 是否删除 1：已删除；0：未删除
     */
    @TableField("is_delete")
    private Integer isDelete;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
