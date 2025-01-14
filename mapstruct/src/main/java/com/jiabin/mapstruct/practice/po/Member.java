package com.jiabin.mapstruct.practice.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 购物会员
 * @author jiabin.yu 2021/10/12.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Member {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private Date birthday;
    private String phone;
    private String icon;
    private Integer gender;
}
