package com.java.webapp.servlet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.java.webapp.servlet.model.Product;
import com.java.webapp.servlet.service.LoginService;
import com.java.webapp.servlet.service.LoginServiceSessionImpl;
import com.java.webapp.servlet.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/products", "/products/json"})
public class ProductsController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = objectMapper.readValue(jsonStream, Product.class);
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter writer = resp.getWriter();) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("    <head>");
            writer.println("        <meta charset=\"UTF-8\">");
            writer.println("        <title>Products | JSON</title>");
            writer.println("    </head>");
            writer.println("    <body>");
            writer.println("        <div>");
            writer.println("            <h1>Products | JSON</h1>");
            writer.println("            <ul>");
            writer.println("                <li>Id " + product.getId() + "</l1>");
            writer.println("                <li>Name " + product.getName() + "</l1>");
            writer.println("                <li>Category " + product.getCategory() + "</l1>");
            writer.println("                <li>Price " + product.getPrice() + "</l1>");
            writer.println("            </ul>");
            writer.println("        </div>");
            writer.println("    </body>");
            writer.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ProductService productService = new ProductService(connection);
        List<Product> products = productService.getAll();

        LoginService loginService = new LoginServiceSessionImpl();
        Optional<String> username = loginService.getUsername(req);

        String reqMessage = (String) req.getAttribute("request key"); // ONE EVERY REQUEST
        String appMessage = (String) getServletContext().getAttribute("context key"); // SINGLETON

        resp.setContentType("text/html;charset=UTF-8");
        boolean isJson = req.getServletPath().endsWith("json");

        if (isJson) {
            resp.setContentType("application/json;");
            try (PrintWriter writer = resp.getWriter()) {
                Gson gson = new Gson();
                writer.print(gson.toJson(products));
            }
        }

        req.setAttribute("products", products);
        req.setAttribute("username", username);

        getServletContext().getRequestDispatcher("/products.jsp").forward(req, resp);
    }
}
