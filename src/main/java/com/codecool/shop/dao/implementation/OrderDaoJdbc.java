package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.OrderStatus;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoJdbc implements OrderDao {
    private DataSource dataSource;

    public OrderDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Order order) {
        int orderId = order.getOrderId();
        int userId = order.getUserId();
        Timestamp timestamp = order.getOrderDate();
        OrderStatus orderStatus = order.getOrderStatus();

        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO orders (order_id, user_id, order_date, order_status)
                    VALUES(?, ?, ?, ?)
                    """);

            statement.setInt(1, orderId);
            statement.setInt(2, userId);
            statement.setTimestamp(3, timestamp);
            statement.setObject(4, orderStatus);

            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Order find(Order order) {
        Order orderResult = null;

        int orderId = order.getOrderId();
        int userId = order.getUserId();
        Timestamp orderDate = order.getOrderDate();
        OrderStatus orderStatus = order.getOrderStatus();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM orders
                    WHERE order_id = ? OR user_id = ? OR order_date = ? OR order_status = ?
                    """);

            statement.setInt(1, orderId);
            statement.setInt(2, userId);
            statement.setTimestamp(3, orderDate);
            statement.setObject(4, orderStatus);

            ResultSet results = statement.executeQuery();
            results.next();

            orderResult = new Order(
                    results.getInt(0),
                    results.getInt(1),
                    results.getTimestamp(2),
                    results.getObject(3, OrderStatus.class));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return orderResult;
    }

    public void remove(Order order) {
        this.remove(order.getOrderId());
    }

    @Override
    public void remove(int orderId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM orders
                    WHERE order_id = ?
                    """);

            statement.setInt(1, orderId);

            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM carts
                    """);

            ResultSet results = statement.executeQuery();
            while (results.next()) {
                orders.add(new Order(results.getInt(0), results.getInt(1), results.getTimestamp(2), results.getObject(3, OrderStatus.class)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return orders;
    }
}
