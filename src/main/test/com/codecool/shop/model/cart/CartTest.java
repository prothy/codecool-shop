package com.codecool.shop.model.cart;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.products.Cloud;
import com.codecool.shop.model.products.Product;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartTest {

    private Cloud cloud;

    @BeforeEach
    public void setUp() {
        cloud = new Cloud(0, "Google Drive", "",
                new ProductCategory(2, "Cloud"), new Supplier(2, "Google"), "",
                new BigDecimal(20), new BigDecimal(20), new BigDecimal(20));
    }

    @Test
    public void productIsAlreadyExists() {
        assertNotNull(cloud);
    }

    @Test
    public void addProductToCart() {

        Product product = mock(Product.class);
        Cart cart = new Cart();
        cart.addProduct(cloud);
        when(product.getName()).thenReturn(cloud.getName());

        assertFalse(cart.getContent().isEmpty());

    }

    @Test
    public void removeProductFromCart() {

        Product product = mock(Product.class);
        Cart cart = new Cart();
        cart.removeProduct(cloud);
        when(product.getProductCategory()).thenReturn(cloud.getProductCategory());

        assertTrue(cart.getContent().isEmpty());

    }



}