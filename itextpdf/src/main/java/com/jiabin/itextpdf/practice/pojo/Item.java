package com.jiabin.itextpdf.practice.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {
    private String sku;
    private String name;
    private Integer quantity;
    private BigDecimal price;
}
