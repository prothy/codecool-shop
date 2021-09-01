package com.codecool.shop.model.products;

import java.math.BigDecimal;

public abstract class SubscriptionProduct extends Product {
    protected BigDecimal yearlyPrice;
    protected BigDecimal monthlyPrice;

    public BigDecimal getYearlyPrice() {
        return yearlyPrice;
    }

    public void setYearlyPrice(BigDecimal yearlyPrice) {
        this.yearlyPrice = yearlyPrice;
    }

    public BigDecimal getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(BigDecimal monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }
}
