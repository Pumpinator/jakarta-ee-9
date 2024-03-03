package com.java.webapp.servlet.model;

import java.time.LocalDate;

public class Product {
    private Long id;
    private String name;
    private Category category;
    private double price;
    private String sku;
    private LocalDate registerDate;

    public Product() {
    }

    public Product(Long id, String name, Category category, double price) {
        this.setId(id);
        this.setName(name);
        this.setCategory(category);
        this.setPrice(price);
    }

    public Product(Long id, String name, Category category, double price, String sku, LocalDate registerDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.sku = sku;
        this.registerDate = registerDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }
}
