package com.jiabin.easy.excel.practice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商品
 * @author jiabin.yu 2021/10/12.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Product {
    private Long id;
    private String productSn;
    private String name;
    private String subTitle;
    private String brandName;
    private BigDecimal price;
    private Integer count;
}
