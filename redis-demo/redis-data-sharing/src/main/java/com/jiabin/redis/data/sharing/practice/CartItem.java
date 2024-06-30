package com.jiabin.redis.data.sharing.practice;

public class CartItem {
    private String productId;
    private int quantity;
    // 省略构造函数、getter和setter方法


    public CartItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
