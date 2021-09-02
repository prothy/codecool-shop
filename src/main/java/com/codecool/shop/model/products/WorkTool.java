package com.codecool.shop.model.products;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.math.BigDecimal;
import java.util.Currency;

public class WorkTool extends SubscriptionProduct{
    public WorkTool(Currency defaultCurrency, String description, ProductCategory productCategory, Supplier supplier, String image, BigDecimal price, BigDecimal yearlyPrice, BigDecimal monthlyPrice) {
        super(defaultCurrency, description, productCategory, supplier, image, price, yearlyPrice, monthlyPrice);
    }
}
