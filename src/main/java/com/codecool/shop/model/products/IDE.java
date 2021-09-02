package com.codecool.shop.model.products;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

public class IDE extends SubscriptionProduct{
    private String languages;

    public IDE(int id, String name, Currency defaultCurrency, String description, ProductCategory productCategory, Supplier supplier, String image, BigDecimal price, BigDecimal yearlyPrice, BigDecimal monthlyPrice, String languages) {
        super(id, name, defaultCurrency, description, productCategory, supplier, image, price, yearlyPrice, monthlyPrice);
        this.languages = languages;
    }
}
