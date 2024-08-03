package com.jiabin.annotation.validation.practice.entity;

import com.jiabin.annotation.validation.practice.valid.Sex;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>
 * User
 * </p>
 *
 * @author jiabin.yu
 * @since 2024/6/11
 */
public class User {

    @NotBlank(message = "用户名不能为空！")
    private String userName;

    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "邮箱不能为空！")
    private String email;

    @NotBlank(message = "密码不能为空！")
    @Size(min = 8, max = 16,message = "请输入长度在8～16位的密码")
    private String userPwd;

    @NotBlank(message = "确认密码不能为空！")
    private String confirmPwd;

    /**
     * 自定义注解校验
     */
    @Sex(message = "性别输入有误！")
    private String sex;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

