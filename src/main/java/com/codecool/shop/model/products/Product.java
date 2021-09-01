package com.codecool.shop.model.products;

import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.math.BigDecimal;
import java.util.Currency;

public abstract class Product extends BaseModel {

    protected Currency defaultCurrency;
    protected String description;
    protected ProductCategory productCategory;
    protected Supplier supplier;
    protected String image;
    protected BigDecimal price;

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        this.productCategory.addProduct(this);
    }

    public String getPrice() {
        return price + " " + defaultCurrency.toString();
    }

    public void setPrice(BigDecimal price, String currency) {
        this.price = price;
        this.defaultCurrency = Currency.getInstance(currency);
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
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", defaultCurrency=" + defaultCurrency +
                ", description='" + description + '\'' +
                ", productCategory=" + productCategory +
                ", supplier=" + supplier +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
