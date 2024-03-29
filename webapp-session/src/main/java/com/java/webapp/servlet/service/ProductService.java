package com.java.webapp.servlet.service;

import com.java.webapp.servlet.exception.ConnectionException;
import com.java.webapp.servlet.model.Product;
import com.java.webapp.servlet.repository.ProductRepository;
import com.java.webapp.servlet.repository.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductService {

    private Repository<Product> productRepository;

    public ProductService(Connection connection) {
        this.productRepository = new ProductRepository(connection);
    }

    public List<Product> getAll() {
        try {
            return productRepository.findAll();
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e.getCause());
        }
    }

    public Optional<Product> getById(Long id) {
        try {
            return Optional.ofNullable(productRepository.findById(id));
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e.getCause());
        }
    }

    public void save(Product product) {
        try {
            productRepository.save(product);
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e.getCause());
        }
    }

    public boolean deleteById(Long id) {
        try {
            return productRepository.deleteById(id);
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e.getCause());
        }
    }
}
