package com.codecool.shop.model.products;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.math.BigDecimal;

public class Cloud extends SubscriptionProduct{
    public Cloud(int id , String name, String description, ProductCategory productCategory, Supplier supplier, String image, BigDecimal price, BigDecimal yearlyPrice, BigDecimal monthlyPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productCategory = productCategory;
        this.supplier = supplier;
        this.image = image;
        this.price = price;
        this.yearlyPrice = yearlyPrice;
        this.monthlyPrice = monthlyPrice;
        this.setPrice(price, "USD");
    }
}
