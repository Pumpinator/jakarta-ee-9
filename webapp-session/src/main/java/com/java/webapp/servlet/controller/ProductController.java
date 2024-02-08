package com.java.webapp.servlet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.List;
import java.util.Optional;

@WebServlet({"/products", "/products/json"})
public class ProductController extends HttpServlet {
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
        ProductService productService = new ProductService();
        List<Product> products = productService.getAll();

        LoginService loginService = new LoginServiceSessionImpl();
        Optional<String> username = loginService.getUsername(req);

        String reqMessage = (String) req.getAttribute("request key"); // ONE EVERY REQUEST
        String appMessage = (String) getServletContext().getAttribute("context key"); // SINGLETON

        resp.setContentType("text/html;charset=UTF-8");
        boolean isJson = req.getServletPath().endsWith("json");

        if (isJson) {
            resp.setContentType("application/json");
        }

        try (PrintWriter writer = resp.getWriter();) {
            if (!isJson) {
                writer.println("<!DOCTYPE html>");
                writer.println("<html>");
                writer.println("    <head>");
                writer.println("        <meta charset=\"UTF-8\">");
                writer.println("        <title>Products | Session</title>");
                writer.println("    </head>");
                writer.println("    <body>");
                writer.println("        <div>");
                writer.println("            <h1>Products | Session</h1>");
                if (username.isPresent())
                    writer.println("<div style='color: blue;'>Welcome, " + username.get() + "</div>");
                writer.println("            <table>");
                writer.println("                <thead>");
                writer.println("                    <tr>");
                writer.println("                        <th>ID</th>");
                writer.println("                        <th>NAME</th>");
                writer.println("                        <th>CATEGORY</th>");
                if (username.isPresent()) {
                    writer.println("                    <th>PRICE</th>");
                    writer.println("                    <th></th>");
                }
                writer.println("                    </tr>");
                writer.println("                </thead>");
                writer.println("                <tbody>");
                products.forEach(product -> {
                    writer.println("                <tr>");
                    writer.println("                    <td>" + product.getId() + "</td>");
                    writer.println("                    <td>" + product.getName() + "</td>");
                    writer.println("                    <td>" + product.getCategory() + "</td>");
                    if (username.isPresent()) {
                        writer.println("                <td>" + product.getPrice() + "</td>");
                        writer.println("                <td><a href='" + req.getContextPath() + "/cart/add?id=" + product.getId() + "'>/cart/add?id=" + product.getId() + "</a></td>");
                    }
                    writer.println("                </tr>");
                });
                writer.println("                </tbody>");
                writer.println("            </table>");
                writer.println("            <a href=\"products/excel\">Export XLS</a>");
                writer.println("            <a href=\"products/json\">Export JSON</a>");
                writer.println("            <a href=\"/webapp-session\"}>Go back...</a>");
                writer.println("        </div>");
                writer.println("        <div>");
                writer.println("            <h1>App Message -> " + appMessage + "</h1>");
                writer.println("            <h1>Req Message -> " + reqMessage + "</h1>");
                writer.println("        </div>");
                writer.println("      </body>");
                writer.println("</html>");
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(products);
                writer.write(json);
            }
        }
    }
}
