package com.jiabin.mapstruct.practice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 购物会员Dto
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberDto {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    //与PO类型不同的属性
    private String birthday;
    //与PO名称不同的属性
    private String phoneNumber;
    private String icon;
    private Integer gender;
}
