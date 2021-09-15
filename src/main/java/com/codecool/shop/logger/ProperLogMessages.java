package com.codecool.shop.logger;

import com.codecool.shop.dao.implementation.CartDaoJdbc;
import com.codecool.shop.model.OrderModel;
import com.codecool.shop.model.cart.Cart;
import com.codecool.shop.model.user.User;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.slf4j.LoggerFactory;
import org.apache.log4j.Logger;

import java.sql.Timestamp;

public class ProperLogMessages {

    private final Logger loggerForCart = Logger.getLogger(Cart.class.getName());
    //private final org.slf4j.Logger loggerForCart = org.slf4j.LoggerFactory.getLogger(Cart.class);
    private final org.slf4j.Logger loggerForUser = org.slf4j.LoggerFactory.getLogger(User.class);
    private final org.slf4j.Logger loggerForOrder = org.slf4j.LoggerFactory.getLogger(OrderModel.class);


    public void addProductToCartLog(String productName) {
        loggerForCart.info(productName +  " has been added to the cart.");
    }

    public void addProductToCartWarning(String productName) {
        loggerForCart.warn(productName +  " was not added to the cart!");
    }

    public void removeProductFromCartLog(String productName) {
        loggerForCart.info(productName + " has been removed from the cart.");
    }

    public void removeProductFromCartWarning(String productName) {
        loggerForCart.warn(productName +  " was not removed to the cart!");
    }

    public void userHasBeenRegisteredToTheWebShop(String userName) {
        loggerForUser.info(userName + " has been registered into the web shop.");
    }

    public void userHasBeenRegisteredToTheWebShopWarning(String userName) {
        loggerForUser.warn(userName + " could not register into the web shop!");
    }

    public void userHasBeenLoggedIntoTheWebShop(String userName) {
        loggerForUser.info(userName + " has been logged into the web shop.");
    }

    public void userHasBeenLoggedIntoTheWebShopWarning(String userName) {
        loggerForUser.warn(userName + " could not login into the web shop!");
    }

    public void userHasBeenRemovedFromTheDatabase(String userName) {
        loggerForUser.info(userName + " has been removed from the database.");
    }

    public void userHasBeenRemovedFromTheDatabaseWarning(String userName) {
        loggerForUser.warn(userName + " could not remove from the database!");
    }

    public void getOrderInformation(String orderStatus, Timestamp orderDate, int userId) {
        loggerForOrder.info("An order has been ordered on: " + orderDate + " | from this user: " + userId +
                " | apparently the order status is: " + orderStatus);
    }

    public void getOrderInformationWarning(String orderStatus, Timestamp orderDate, int userId) {
        loggerForOrder.warn("An order has been ordered on: " + orderDate + " | from this user: " + userId +
                " | apparently the order status is: " + "FAILED");
    }

}
