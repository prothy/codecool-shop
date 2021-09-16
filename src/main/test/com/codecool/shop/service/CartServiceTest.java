package com.codecool.shop.service;

import com.codecool.shop.model.cart.CartItem;
import com.codecool.shop.model.products.IDE;
import com.codecool.shop.model.products.OS;
import com.codecool.shop.model.products.Product;
import org.junit.Before;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class CartServiceTest {
    CartService cartService;
    Product ide, os;

    @BeforeEach
    void setup() {
        cartService = new CartService();

        ide = new IDE();
        ide.setId(0);

        os = new OS();
        os.setId(1);
    }

    @Test
    void addToCartAddsParticularQuantity() {
        //Arrange
        int quantity1 = (int) (Math.random() * 10);
        int quantity2 = (int) (Math.random() * 10);

        List<CartItem> testCart = new ArrayList<>();
        CartItem testItem1 = new CartItem(ide, quantity1);
        CartItem testItem2 = new CartItem(os, quantity2);

        testCart.add(testItem1);
        testCart.add(testItem2);

        //Act
        cartService.addToCart(ide, quantity1);
        cartService.addToCart(os, quantity2);

        //Assert
        assertEquals(cartService.getCart().size(), testCart.size());
        assertEquals(cartService.getCart().get(0).getProduct(), testCart.get(0).getProduct());
        assertEquals(cartService.getCart().get(1).getProduct(), testCart.get(1).getProduct());
    }


    @Test
    void removeFromCartRemovesParticularQuantity() {
        //Arrange
        int quantity1 = (int) (Math.random() * 10);
        int quantity2 = (int) (Math.random() * 10);

        List<CartItem> testCart = new ArrayList<>();
        CartItem testItem1 = new CartItem(ide, quantity1);
        CartItem testItem2 = new CartItem(os, quantity2);

        testCart.add(testItem1);

        cartService.setCart(testCart);

        testCart.add(testItem2);

        //Act
        cartService.addToCart(ide, quantity1);
        cartService.addToCart(os, quantity2);
        cartService.removeFromCart(os, quantity2);

        //Assert
        assertEquals(cartService.getCart().size(), testCart.size());
        assertEquals(cartService.getCart().get(0).getProduct(), testCart.get(0).getProduct());
    }


}
