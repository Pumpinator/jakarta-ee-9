package com.java.webapp.servlet.service;

import com.java.webapp.servlet.model.Product;
import com.java.webapp.servlet.repository.ProductRepository;
import com.java.webapp.servlet.repository.Repository;

import java.util.List;
import java.util.Optional;

public class ProductService {

    private Repository<Product> productRepository = new ProductRepository();

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public List<Product> getAll()  {
        return productRepository.findAll();
    }

    public Optional<Product> getById(Long id) {return  productRepository.findById(id);}
}
