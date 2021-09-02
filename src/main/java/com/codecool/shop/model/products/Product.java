package com.codecool.shop.model.products;

import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.math.BigDecimal;
import java.util.Currency;

public abstract class Product extends BaseModel {

    private Currency defaultCurrency;
    private String description;
    private ProductCategory productCategory;
    private Supplier supplier;
    private String image;
    private BigDecimal price;

    public ProductCategory getProductCategory() {
        return productCategory;
    }

//    public void setProductCategory(ProductCategory productCategory) {
//        this.productCategory = productCategory;
//        this.productCategory.addProduct(this);
//    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        this.supplier.addProduct(this);
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public String getPrice() {
        return price.toString();
    };
}
