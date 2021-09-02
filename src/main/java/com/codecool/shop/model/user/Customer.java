package com.codecool.shop.model.user;

import com.codecool.shop.model.*;
import com.codecool.shop.model.cart.Cart;
import com.codecool.shop.model.payment.CreditCard;
import com.codecool.shop.model.payment.PayPal;
import com.codecool.shop.model.payment.Payment;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class Customer extends User{
    private Cart cart;
    private HashSet<Order> orders;
    private BigDecimal wallet;
    private Currency defaultCurrency;
    private HashMap<String, String> paymentDetail;

    public Customer(int id, String name, String email, String password, boolean isAdmin, HashSet<Order> orders, BigDecimal wallet, String defaultCurrency) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.cart = new Cart();
        this.orders = orders;
        this.wallet = wallet;
        this.defaultCurrency = Currency.getInstance(defaultCurrency);
    }

    public Customer() {
        this.id = 1;
        this.name = "guest";
        this.email = null;
        this.password = null;
        this.isAdmin = false;
        this.cart = new Cart();
        this.orders = new HashSet<>();
        this.wallet = new BigDecimal(420);
        this.defaultCurrency = Currency.getInstance("EUR");
    }


    public void addOrder() {
        if (confirmOrder()){
            if (isPaymentSuccess(paymentDetail.get("paymentType"))) {
                orders.add(new Order(1, this.cart));
            }
        }
    }

    public void cancelOrder(int id) {
        Order chosenOrder = orders.stream()
                .parallel().filter(order -> order.getId() == id)
                .findFirst().orElseThrow(NoSuchElementException::new);
        orders.remove(chosenOrder);
    }

    public Cart getCart() {
        return cart;
    }

    private boolean confirmOrder() {
        OrderValidation valid = new OrderValidation(this.name, this.email);
        return valid.everythingIsValid();
    }

    private boolean isPaymentSuccess(String payment) {
        switch (payment){
            case "creditCard":
                CreditCard creditCard  = new CreditCard(new HashMap<String, String>());
                creditCard.validateDetails();
                return creditCard.isSuccess();
            case "paypal": // [TODO]: not implemented
                PayPal payPal = new PayPal(new HashMap<String, String>());
                return payPal.isSuccess();
            case "wallet":
                return decreaseWallet();
            default:
                return false;
        }
    }

    private boolean decreaseWallet() {
        String sumPriceString = cart.getSumPrice().split(" ")[0];
        BigDecimal sumPrice = BigDecimal.valueOf(Long.parseLong(sumPriceString));
        if (sumPrice.compareTo(wallet) > 0) {
            return false;
        }
        wallet = wallet.subtract(sumPrice);
        return true;

    }


    public HashSet<Order> getOrders() {
        return orders;
    }

    public void setPaymentDetail(HashMap<String, String> paymentDetail) {
        this.paymentDetail = paymentDetail;
    }
}
