package com.java.webapp.servlet.repository;

import com.java.webapp.servlet.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements Repository<Category> {
    private Connection connection;

    public CategoryRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Category> findAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";
        System.out.println(query);
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                categories.add(getCategory(resultSet));
            }
        }
        return categories;
    }

    @Override
    public Category findById(Long id) throws SQLException {
        Category category = null;
        String query = "SELECT * FROM categories c WHERE c.id = ?";
        System.out.println(query);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    category = getCategory(resultSet);
                }
            }
        }
        return category;
    }

    @Override
    public void save(Category category) throws SQLException {
        String query = isUpdating(category)
                ? "UPDATE categories c SET c.name = ? WHERE c.id = ?"
                : "INSERT INTO categories (name) VALUES (?)";
        System.out.println(query);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, category.getName());
            if (isUpdating(category)) preparedStatement.setLong(2, category.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        String query = "DELETE FROM categories c WHER c.id = ?";
        System.out.println(query);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        }
    }

    @Override
    public boolean isUpdating(Category category) {
        return (category.getId() != null || category.getId() > 0);
    }

    private static Category getCategory(ResultSet resultSet) throws SQLException {
        return new Category(resultSet.getLong("id"), resultSet.getString("name"));
    }
}
