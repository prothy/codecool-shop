package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.products.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoJDBC implements ProductDao {
    private final DataSource dataSource;
    private final ProductCategoryDao productCategoryDao;
    private final SupplierDao supplierDao;

    public ProductDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
        productCategoryDao = new ProductCategoryDaoJDBC(dataSource);
        supplierDao = new SupplierDaoJDBC(dataSource);
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
        try (Connection conn = dataSource.getConnection()){
            String sql = "SELECT * FROM products WHERE product_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) return null; // first row was not found == no data was returned by the query

            ProductCategory productCategory = productCategoryDao.find(rs.getInt(3));
            Supplier supplier = supplierDao.find(rs.getInt(4));

            return createFoundObject(productCategory, supplier, rs);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM products";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> result = new ArrayList<>();
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                ProductCategory productCategory = productCategoryDao.find(rs.getInt(3));
                Supplier supplier = supplierDao.find(rs.getInt(4));
                Product product = createFoundObject(productCategory, supplier, rs);
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }
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
        return getAll().stream()
                .filter(product -> product.getProductCategory().getId() == productCategory.getId())
                .collect(Collectors.toList());
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

    private Product createFoundObject(ProductCategory productCategory, Supplier supplier, ResultSet result) throws SQLException {
        switch (productCategory.getId()) {
            case 0:
                return new OS(result.getInt(1), result.getString(2), result.getString(5), productCategory, supplier, result.getString(12), result.getBigDecimal(7), result.getInt(10));
            case 1:
                return new IDE(result.getInt(1), result.getString(2), result.getString(5), productCategory, supplier, result.getString(12), result.getBigDecimal(7), result.getBigDecimal(8), result.getBigDecimal(9), result.getString(6));
            case 2:
                return new Cloud(result.getInt(1), result.getString(2), result.getString(5), productCategory, supplier, result.getString(12), result.getBigDecimal(7), result.getBigDecimal(8), result.getBigDecimal(9));
            case 3:
                return new WorkTool(result.getInt(1), result.getString(2), result.getString(5), productCategory, supplier, result.getString(12), result.getBigDecimal(7), result.getBigDecimal(8), result.getBigDecimal(9));
            default:
                return null;
        }

    }
}
