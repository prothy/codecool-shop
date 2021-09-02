package com.codecool.shop.model.products;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.math.BigDecimal;
import java.util.Currency;

public class Cloud extends SubscriptionProduct{
    public Cloud(int id, String name, Currency defaultCurrency, String description, ProductCategory productCategory, Supplier supplier, String image, BigDecimal price, BigDecimal yearlyPrice, BigDecimal monthlyPrice) {
        super(id, name, defaultCurrency, description, productCategory, supplier, image, price, yearlyPrice, monthlyPrice);
    }
}
