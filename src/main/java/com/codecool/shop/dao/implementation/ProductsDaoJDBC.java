package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.products.*;

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
            if (product instanceof IDE) addNewIDEToDB(conn, product);
            if (product instanceof Cloud) addNewCloud(conn, product);
            if (product instanceof WorkTool) addNewWorkTool(conn, product);
            if (product instanceof OS) addNewOS(conn, product);

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

    private void addNewIDEToDB(Connection conn, Product product) throws SQLException {
        String sql;
        PreparedStatement statement;

        sql = "INSERT INTO products (product_name, product_category_id, supplier_id, description, languages, yearlyprice, monthlyprice, currency, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, product.getName());
        statement.setInt(2, product.getProductCategory().getId());
        statement.setInt(3, product.getSupplier().getId());
        statement.setString(4, product.getDescription());
        statement.setString(5, ((IDE) product).getLanguages());
        statement.setBigDecimal(6, ((IDE) product).getYearlyPrice());
        statement.setBigDecimal(7, ((IDE) product).getMonthlyPrice());
        statement.setString(8, product.getDefaultCurrencyString());
        statement.setString(9, product.getImage());
        statement.executeUpdate();
    }

    private void addNewCloud(Connection conn, Product product) throws SQLException {
        String sql;
        PreparedStatement statement;

        sql = "INSERT INTO products (product_name, product_category_id, supplier_id, description, yearlyprice, monthlyprice, currency, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, product.getName());
        statement.setInt(2, product.getProductCategory().getId());
        statement.setInt(3, product.getSupplier().getId());
        statement.setString(4, product.getDescription());
        statement.setBigDecimal(5, ((Cloud) product).getYearlyPrice());
        statement.setBigDecimal(6, ((Cloud) product).getMonthlyPrice());
        statement.setString(7, product.getDefaultCurrencyString());
        statement.setString(8, product.getImage());
        statement.executeUpdate();
    }

    private void addNewWorkTool(Connection conn, Product product) throws SQLException {
        String sql;
        PreparedStatement statement;

        sql = "INSERT INTO products (product_name, product_category_id, supplier_id, description, yearlyprice, monthlyprice, currency, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, product.getName());
        statement.setInt(2, product.getProductCategory().getId());
        statement.setInt(3, product.getSupplier().getId());
        statement.setString(4, product.getDescription());
        statement.setBigDecimal(5, ((WorkTool) product).getYearlyPrice());
        statement.setBigDecimal(6, ((WorkTool) product).getMonthlyPrice());
        statement.setString(7, product.getDefaultCurrencyString());
        statement.setString(8, product.getImage());
        statement.executeUpdate();
    }

    private void addNewOS(Connection conn, Product product) throws SQLException {
        String sql;
        PreparedStatement statement;

        sql = "INSERT INTO products (product_name, product_category_id, supplier_id, description, price, bitversion,  currency, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, product.getName());
        statement.setInt(2, product.getProductCategory().getId());
        statement.setInt(3, product.getSupplier().getId());
        statement.setString(4, product.getDescription());
        statement.setBigDecimal(5, product.getPriceAsBigDecimal());
        statement.setInt(6, ((OS) product).getBitVersion());
        statement.setString(7, product.getDefaultCurrencyString());
        statement.setString(8, product.getImage());
        statement.executeUpdate();
    }
}
