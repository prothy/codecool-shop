package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.cart.CartItem;
import com.codecool.shop.model.products.Product;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.ProductService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoJdbc implements CartDao {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(CartDaoJdbc.class);
    private DataSource ds;

    public CartDaoJdbc(DataSource ds) {
        try {
            this.ds = ds;
            connection = ds.getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Removes all entries that contain the user id of the cart, and adds the content of the cart again.
     * @param cart
     */
    @Override
    public void updateCart(CartService cart) {
        int userId = cart.getUserId();
        try {
//            this.remove(userId);
            this.add(cart);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    private void add(CartService cart) throws SQLException {
        int userId = cart.getUserId();
        List<CartItem> cartItems = cart.getCart();

        StringBuilder query = new StringBuilder();

        String clearCart = String.format("""
                DELETE FROM carts
                WHERE user_id = %o;
                """, userId);
        query.append(clearCart);

        for (CartItem cartItem : cartItems) {
            int quantity = cartItem.getQuantity();
            int productId = cartItem.getProduct().getId();

            String queryString = String.format("""
                    INSERT INTO carts
                    VALUES  (%o, %o, %o);
                    """, userId, productId, quantity);
            query.append(queryString);
        }

        PreparedStatement statement = connection.prepareStatement(query.toString());
        statement.executeQuery();
    }

    @Override
    public List<CartItem> findAll(int userId) {
        List<CartItem> cartContent = new ArrayList<>();

        // product service needed to generate product instance for use in adding new cart item to list
        ProductDaoJDBC productDao = new ProductDaoJDBC(ds);
        ProductService productService = new ProductService(productDao);

        try {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM carts
                    WHERE user_id = ?
                    """);

            statement.setInt(1, userId);

            ResultSet results = statement.executeQuery();
            while (results.next()) {
                Product product = productService.getProductById(results.getInt(1));
                int quantity = results.getInt(3);
                cartContent.add(new CartItem(product, quantity));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cartContent;
    }

    @Override
    public void remove(int userId, int productId) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM carts
                    WHERE user_id = ? AND product_id = ?
                    """);

            statement.setInt(1, userId);
            statement.setInt(2, productId);

            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void remove(int userId) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM carts
                    WHERE user_id = ?
                    """);

            statement.setInt(1, userId);

            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void clearCart(int userId) {
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM carts
                    WHERE user_id = ?
                    """);

            statement.setInt(1, userId);

            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<CartService> getAll() {
        List<CartService> cartContent = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM carts
                    """);

            ResultSet results = statement.executeQuery();
            while (results.next()) {
                cartContent.add(new CartService(results.getInt(0), results.getInt(1)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cartContent;
    }
}
