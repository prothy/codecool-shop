package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.products.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {
    private final DataSource dataSource;

    public ProductCategoryDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {
        // Implement adding new Category
    }

    @Override
    public ProductCategory find(int id) {
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
    public List<ProductCategory> getAll() {
        return null;
    }
}
