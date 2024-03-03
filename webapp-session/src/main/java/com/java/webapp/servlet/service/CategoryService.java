package com.java.webapp.servlet.service;

import com.java.webapp.servlet.exception.ConnectionException;
import com.java.webapp.servlet.model.Category;
import com.java.webapp.servlet.model.Product;
import com.java.webapp.servlet.repository.CategoryRepository;
import com.java.webapp.servlet.repository.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoryService {
    private Repository<Category> categoryRepository;

    public CategoryService(Connection connection) {
        this.categoryRepository = new CategoryRepository(connection);
    }

    public List<Category> getAll() {
        try {
            return categoryRepository.findAll();
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e.getCause());
        }
    }

    public Optional<Category> getById(Long id) {
        try {
            return Optional.ofNullable(categoryRepository.findById(id));
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e.getCause());
        }
    }

    public void save(Category category) {
        try {
            categoryRepository.save(category);
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e.getCause());
        }
    }

    public boolean deleteById(Long id) {
        try {
            return categoryRepository.deleteById(id);
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e.getCause());
        }
    }
}
