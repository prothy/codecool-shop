package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.products.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SupplierDaoJDBC implements SupplierDao {
    private final DataSource dataSource;

    public SupplierDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {
        // implement adding new Supplier to DB
    }

    @Override
    public Supplier find(int id) {
        try (Connection conn = dataSource.getConnection()){
            String sql = "SELECT * FROM product_categories WHERE category_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) return null; // first row was not found == no data was returned by the query

            return new ProductCategory(rs.getInt(1), rs.getString(2));

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getDataFromJson() {
        return null;
    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
