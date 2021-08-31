package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.Currency;

public abstract class Product extends BaseModel {

    protected Currency defaultCurrency;
    protected String description;
    protected ProductCategory productCategory;
    protected Supplier supplier;
    protected String image;



//    this.setPrice(defaultPrice, currencyString);
//        this.setSupplier(supplier);
//        this.setProductCategory(productCategory);


    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

//    public String getPrice() {
//        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
//    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        this.productCategory.addProduct(this);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        this.supplier.addProduct(this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, " +
                        "defaultCurrency: %4$s, " +
                        "productCategory: %5$s, " +
                        "supplier: %6$s",
                this.id,
                this.name,
                this.defaultCurrency.toString(),
                this.productCategory.getName(),
                this.supplier.getName());
    }
}
