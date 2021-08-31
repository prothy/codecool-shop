package com.codecool.shop.model.user;

import com.codecool.shop.model.Order;

import java.util.Currency;
import java.util.HashSet;
import java.util.NoSuchElementException;

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

    public void addOrder() {
        orders.add(new Order(1));
    }

    public void cancelOrder(int id) {
        Order chosenOrder = orders.stream()
                .parallel().filter(order -> order.getId() == id)
                .findFirst().orElseThrow(NoSuchElementException::new);
        orders.remove(chosenOrder);
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
