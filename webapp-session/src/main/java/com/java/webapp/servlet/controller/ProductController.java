package com.java.webapp.servlet.controller;

import com.java.webapp.servlet.model.Category;
import com.java.webapp.servlet.model.Product;
import com.java.webapp.servlet.service.CategoryService;
import com.java.webapp.servlet.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet({"/product", "/product/delete"})
public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().endsWith("delete")) {
            doDelete(req, resp);
        } else {
            Connection connection = (Connection) req.getAttribute("connection");
            CategoryService categoryService = new CategoryService(connection);
            ProductService productService = new ProductService(connection);
            Long id;
            try {
                id = Long.valueOf(req.getParameter("id"));
            } catch (NumberFormatException e) {
                id = 0L;
            }
            Product product = new Product();
            product.setCategory(new Category());
            if (id > 0) {
                Optional<Product> productOptional = productService.getById(id);
                if (productOptional.isPresent())
                    product = productOptional.get();
            }
            req.setAttribute("categories", categoryService.getAll());
            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/product.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ProductService productService = new ProductService(connection);
        CategoryService categoryService = new CategoryService(connection);
        String name = req.getParameter("name");
        Double price;
        try {
            price = Double.valueOf((req.getParameter("price")));
        } catch (NumberFormatException e) {
            price = (double) 0;
        }
        String sku = req.getParameter("sku");
        String registerDate = req.getParameter("register_date");
        Long categoryId;
        try {
            categoryId = Long.valueOf(req.getParameter("category"));
        } catch (NumberFormatException e) {
            categoryId = 0L;
        }
        Map<String, String> errors = new HashMap<>();
        if (name == null || name.isBlank())
            errors.put("name", "The name is required");
        if (sku == null || sku.isBlank())
            errors.put("sku", "Sku is required");
        if (sku.length() > 10)
            errors.put("sku", "The sku maxium length is 10 characters.");
        if (registerDate == null || registerDate.isBlank())
            errors.put("register_date", "Register date is required");
        if (price.equals(0.0))
            errors.put("price", "The price is required");
        if (categoryId.equals(0L))
            errors.put("category", "The category is required");

        LocalDate date;
        try {
            date = LocalDate.parse(registerDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            date = null;
        }
        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setSku(sku);
        product.setPrice(price);
        product.setRegisterDate(date);
        Category category = new Category();
        category.setId(categoryId);
        product.setCategory(category);

        if (errors.isEmpty()) {
            productService.save(product);
            resp.sendRedirect(req.getContextPath() + "/products");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("categories", categoryService.getAll());
            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/product.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ProductService productService = new ProductService(connection);
        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        if (id > 0) {
            Optional<Product> productOptional = productService.getById(id);
            if (productOptional.isPresent()) {
                productService.deleteById(id);
                resp.sendRedirect(req.getContextPath() + "/products");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "The product do not exist.");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "The product id is required.");
        }
    }
}
