package com.java.webapp.servlet.repository;

import com.java.webapp.servlet.model.Product;

import java.util.Arrays;
import java.util.List;

public class ProductRepository implements Repository{
    @Override
    public List findAll() {
        return Arrays.asList(
                new Product(1L, "ASUS Zenbook 14X", "Electronics", 1099.99),
                new Product(2L, "LG 34 Curved Ultrawide", "Electronics", 326.00),
                new Product(3L, "Keychron K3 Ultra-Slim", "Electronics", 99.99),
                new Product(4L, "Homall 55\" × 24\"", "Home", 104.99)
        );
    }
}
