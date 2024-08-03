package com.jiabin.data.desensitization.practice.entity;

import com.jiabin.data.desensitization.practice.enums.SensitiveEnum;
import com.jiabin.data.desensitization.practice.sensitive.SensitiveWrapped;

/**
 * <p>
 * UserEntity
 * </p>
 *
 * @author jiabin.yu
 * @since 2024/7/15
 */
public class UserEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 手机号
     */
    @SensitiveWrapped(SensitiveEnum.MOBILE_PHONE)
    private String mobile;

    /**
     * 身份证号码
     */
    @SensitiveWrapped(SensitiveEnum.ID_CARD)
    private String idCard;

    /**
     * 年龄
     */
    private String sex;

    /**
     * 性别
     */
    private int age;

    public Long getUserId() {
        return userId;
    }

    public UserEntity setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public UserEntity setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getIdCard() {
        return idCard;
    }

    public UserEntity setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public UserEntity setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserEntity setAge(int age) {
        this.age = age;
        return this;
    }
}