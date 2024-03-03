package com.java.webapp.servlet.repository;

import com.java.webapp.servlet.model.Category;
import com.java.webapp.servlet.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements Repository<Product> {
    private Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.price, c.name AS category, p.sku, p.register_date, p.category AS id_category FROM products p INNER JOIN categories c ON (p.category = c.id)";
        System.out.println(query);
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                products.add(getProduct(resultSet));
            }
        }
        return products;
    }


    @Override
    public Product findById(Long id) throws SQLException {
        Product product = null;
        String query = "SELECT p.id, p.name, p.price, c.name AS category, p.sku, p.register_date, p.category AS id_category FROM products p INNER JOIN categories c ON (p.category = c.id) WHERE p.id = ? ORDER BY p.id ASC";
        System.out.println(query);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    product = getProduct(resultSet);
                }
            }
        }
        return product;
    }

    @Override
    public void save(Product product) throws SQLException {
        String query = isUpdating(product)
                ? "UPDATE products p SET p.name = ?, p.price = ?, p.sku = ?, p.category = ? WHERE p.id = ?"
                : "INSERT INTO products (name, price, sku, category, register_date) VALUES (?, ?, ?, ?, ?)";
        System.out.println(query);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getSku());
            preparedStatement.setLong(4, product.getCategory().getId());
            if (isUpdating(product)) preparedStatement.setLong(5, product.getId());
            else preparedStatement.setDate(5, Date.valueOf(product.getRegisterDate()));
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        String query = "DELETE FROM products p WHERE p.id = ?";
        System.out.println(query);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        }
    }

    private static Product getProduct(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                new Category(resultSet.getLong("id_category"), resultSet.getString("category")),
                resultSet.getDouble("price"),
                resultSet.getString("sku"),
                resultSet.getDate("register_date").toLocalDate()
        );
    }

    @Override
    public boolean isUpdating(Product product) {
        return (product.getId() > 0);
    }
}
