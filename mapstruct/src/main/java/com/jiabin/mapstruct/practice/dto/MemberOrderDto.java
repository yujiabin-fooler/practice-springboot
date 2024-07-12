package com.jiabin.mapstruct.practice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会员商品信息组合Dto
 * @author jiabin.yu 2021/10/21.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberOrderDto extends MemberDto{
    private String orderSn;
    private String receiverAddress;
}
