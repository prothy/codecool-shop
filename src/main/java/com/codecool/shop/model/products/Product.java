package com.codecool.shop.model.products;

import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.math.BigDecimal;
import java.util.Currency;

public abstract class Product extends BaseModel {

    private Currency defaultCurrency = Currency.getInstance("USD");
    private String description;
    private ProductCategory productCategory;
    private Supplier supplier;
    private String image;
    private BigDecimal price;

    public Product(int id, String name, String description, ProductCategory productCategory, Supplier supplier, String image, BigDecimal price) {
        super(id, name);
        this.description = description;
        this.productCategory = productCategory;
        this.supplier = supplier;
        this.image = image;
        this.price = price;
    }

    public Product() {
        super();
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

//    public void setProductCategory(ProductCategory productCategory) {
//        this.productCategory = productCategory;
//        this.productCategory.addProduct(this);
//    }


    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setPrice(BigDecimal price, String currency) {
        this.price = price;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

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

    public String getDefaultCurrencyString() {
        return defaultCurrency.getCurrencyCode();
    }

    public BigDecimal getPriceAsBigDecimal() {
        return price;
    }

    public String getPrice() {
        return price.toString() + " " + defaultCurrency.toString();
    };
}
