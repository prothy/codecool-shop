package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.products.IDE;
import com.codecool.shop.model.products.Product;
import com.codecool.shop.model.products.SubscriptionProduct;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class ProductsDaoJDBC implements ProductDao {
    private final DataSource dataSource;

    public ProductsDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {
        try (Connection conn = dataSource.getConnection()) {
            String sql;
            PreparedStatement statement = null;

            if (product instanceof IDE) {
                sql = "INSERT INTO products (product_name, product_category_id, supplier_id, description, languages, price, yearlyprice, monthlyprice, currency, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, product.getName());
            }
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<List<Product>> createObjectsFromJson() throws IOException {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }

    @Override
    public Product createObjectFromJson(String jsonElement) {
        return null;
    }
}
