package com.codecool.shop.cart;

import java.math.BigDecimal;

public class ProductDetail {

    private String productName;
    private String price;
    private Integer quantity;
    private BigDecimal sumPrice;

    public ProductDetail(String productName, String price, Integer quantity, BigDecimal sumPrice) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.sumPrice = sumPrice;
    }

}
