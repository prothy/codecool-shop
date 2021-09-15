package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductsOrders;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.products.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProductsOrdersJdbc implements ProductsOrders {
    private final DataSource dataSource;

    public ProductsOrdersJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(int orderId, int productId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO products_orders (order_id , product_id) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, orderId);
            statement.setInt(2, productId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> find(int orderId) {
        return null;
    }
}
