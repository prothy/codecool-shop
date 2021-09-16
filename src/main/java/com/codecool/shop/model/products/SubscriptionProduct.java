package com.codecool.shop.model.products;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.math.BigDecimal;
import java.util.Currency;

public abstract class SubscriptionProduct extends Product {
    private BigDecimal yearlyPrice;
    private BigDecimal monthlyPrice;

    public SubscriptionProduct(int id, String name, String description, ProductCategory productCategory, Supplier supplier, String image, BigDecimal price, BigDecimal yearlyPrice, BigDecimal monthlyPrice) {
        //Todo remove on Second Sprint
        super(id, name, description, productCategory, supplier, image, yearlyPrice);
//        super(id, name, defaultCurrency, description, productCategory, supplier, image, price);
        this.yearlyPrice = yearlyPrice;
        this.monthlyPrice = monthlyPrice;

    }

    public SubscriptionProduct() {
    }

    public BigDecimal getYearlyPrice() {
        return yearlyPrice;
    }

    public BigDecimal getMonthlyPrice() {
        return monthlyPrice;
    }
}
