package com.codecool.shop.model.user;

import java.util.Currency;
import java.util.HashSet;

public class Customer extends User{
    private Cart cart;
    private HashSet<Order> orders;
    private Currency wallet;
    private Payment payment;

    public Customer(int id, String name, String email, String password, boolean isAdmin, HashSet<Order> orders, Currency wallet) {
        super(id, name, email, password, isAdmin);
        this.cart = new Cart;
        this.orders = orders;
        this.wallet = wallet;
    }


}
