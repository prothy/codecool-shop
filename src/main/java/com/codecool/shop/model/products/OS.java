package com.codecool.shop.model.products;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.math.BigDecimal;

public class OS extends Product {
    private int bitVersion;

    public OS(int id , String name, String description, ProductCategory productCategory, Supplier supplier, String image, BigDecimal price, int bitVersion) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productCategory = productCategory;
        this.supplier = supplier;
        this.image = image;
        this.price = price;
        this.bitVersion = bitVersion;
        this.setPrice(price, "USD");
    }

    public int getBitVersion() {
        return bitVersion;
    }

    public void setBitVersion(int bitVersion) {
        this.bitVersion = bitVersion;
    }
}
