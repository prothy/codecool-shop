package com.codecool.shop.model.user;

import java.util.Currency;
import java.util.HashSet;

public class Customer extends User{
    private Cart cart;
    private HashSet<Order> orders;
    private Currency wallet;
    private Payment payment;

    public Customer(int id, String name, String email, String password, boolean isAdmin, HashSet<Order> orders, Currency wallet) {
        super(id, name, email, password, false);
        this.cart = new Cart;
        this.orders = orders;
        this.wallet = wallet;
    }

    public void addOrder() {
        // [TODO]: not implemented
    }

    public void cancelOrder() {
        // [TODO]: not implemented
    }

    private boolean confirmOrder() {
        // [TODO]: not implemented
        return true;
    }

    private String choosePayment() {
        // [TODO]: not implemented
        return "";
    }

    private void decreaseWallet() {
        // [TODO]: not implemented
    }

    private void checkPaymentType(String payment) {
        // [TODO]: not implemented
    }


}
