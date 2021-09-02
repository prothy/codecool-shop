package com.codecool.shop.model.products;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.math.BigDecimal;
import java.util.Currency;

public class WorkTool extends SubscriptionProduct{
    public WorkTool(int id, String name, String description, ProductCategory productCategory, Supplier supplier, String image, BigDecimal price, BigDecimal yearlyPrice, BigDecimal monthlyPrice) {
        super(id, name, description, productCategory, supplier, image, yearlyPrice, yearlyPrice, monthlyPrice);
    }
}
