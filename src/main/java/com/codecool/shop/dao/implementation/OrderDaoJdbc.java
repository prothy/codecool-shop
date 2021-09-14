package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class OrderDaoJdbc implements OrderDao {
    Connection connection;

    public OrderDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Order order) {
        int orderId = order.getOrderId();
        int userId = order.getUserId();
        String orderStatus = order.getOrderStatus();

        try {
            PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO orders (order_id, user_id, order_date, order_status)
                    VALUES(?, ?, CURRENT_TIMESTAMP, ?)
                    """);

            statement.setInt(1, orderId);
            statement.setInt(2, userId);
            statement.setString(3, orderStatus);

            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Order find(Order order) {

        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
