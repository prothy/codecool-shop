package com.codecool.shop.model.user;

import com.codecool.shop.model.*;
import com.codecool.shop.model.cart.Cart;
import com.codecool.shop.model.payment.CreditCard;
import com.codecool.shop.model.payment.PayPal;

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
    private String address;

    public Customer(int id, String name, String email, String password, boolean isAdmin,
                    Cart cart, HashSet<Order> orders, BigDecimal wallet, String defaultCurrency, String address) {
        super(id, name, email, password, isAdmin);
        this.cart = cart;
        this.orders = orders;
        this.wallet = wallet;
        this.defaultCurrency = Currency.getInstance(defaultCurrency);
        this.address = address;
    }

    public Customer() {
        super(1, "guest", null, null, false);
        this.cart = new Cart();
        this.orders = new HashSet<>();
        this.wallet = new BigDecimal(100);
        this.defaultCurrency = Currency.getInstance("USD");
        this.address = null;
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
                .parallel().filter(order -> order.getUserId() == id)
                .findFirst().orElseThrow(NoSuchElementException::new);
        orders.remove(chosenOrder);
    }

    public Cart getCart() {
        return cart;
    }

    private boolean confirmOrder() {
        OrderValidation valid = new OrderValidation(getName(), getEmail());
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

    public BigDecimal getWallet() {
        return wallet;
    }

    public String getDefaultCurrencyString() {
        return defaultCurrency.getCurrencyCode();
    }
    public String getAddress() {
        return address;
    }
}
