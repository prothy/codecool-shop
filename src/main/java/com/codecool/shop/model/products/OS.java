package com.codecool.shop.model.products;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.math.BigDecimal;
import java.util.Currency;

public class OS extends Product {
    private int bitVersion;

    public OS(int id, String name,String description, ProductCategory productCategory, Supplier supplier, String image, BigDecimal price, int bitVersion) {
        super(id, name, description, productCategory, supplier, image, price);
        this.bitVersion = bitVersion;
    }
}
