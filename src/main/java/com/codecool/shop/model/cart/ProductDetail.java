package com.codecool.shop.model.cart;

import java.math.BigDecimal;

public class ProductDetail {

    private String productName;
    private String price;
    private Integer quantity;
    private String sumPrice;

    public ProductDetail(String productName, String price, Integer quantity, String sumPrice) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.sumPrice = sumPrice;
    }

}
