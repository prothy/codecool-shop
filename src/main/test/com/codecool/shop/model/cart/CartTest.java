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

    //Tested class:
    private Cart cart;
    //Dependencies:
    private Cloud cloud;

    @BeforeEach
    public void setUp() {
        //cloud = mock(Cloud.class);
        cloud = new Cloud(0, "Google Drive", "",
                    new ProductCategory(2, "Cloud"), new Supplier(2, "Google"), "",
                    new BigDecimal(12.1), new BigDecimal(44.1),new BigDecimal(231.99));
        cart = new Cart();
    }

    @Test
    public void cartIsExists() {
        assertNotNull(cart);
    }

    @Test
    public void addProductToCartAndCheckingIfContentIsNotEmpty() {

        cart.addProduct(cloud);
        //when(cart.getContent().size()).thenCallRealMethod();
        assertEquals(cart.getContent().size(), 1);

    }

    @Test
    public void removeProductFromCartAndCheckingIfContentIsEmpty() {

        cart.addProduct(cloud);
        cart.removeProduct(cloud);
        //when(cart.getContent().size()).thenCallRealMethod();

        assertEquals(cart.getContent().size(), 0);

    }

}