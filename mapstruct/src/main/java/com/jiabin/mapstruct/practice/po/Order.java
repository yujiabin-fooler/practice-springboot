package com.jiabin.mapstruct.practice.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 订单
 * @author jiabin.yu 2021/10/12.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Order {
    private Long id;
    private String orderSn;
    private Date createTime;
    private String receiverAddress;
    private Member member;
    private List<Product> productList;
}
