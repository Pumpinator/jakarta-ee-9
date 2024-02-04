package com.java.webapp.servlet.controller;

import com.java.webapp.servlet.model.Product;
import com.java.webapp.servlet.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/search")
public class SearchController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductService();
        String name = req.getParameter("name");
        Optional<Product> founded = productService.getAll()
                .stream()
                .filter(product -> {
                    if(name == null || name.isBlank()) {
                        return false;
                    }
                    return product.getName().contains(name);
                }).findFirst();
        if (founded.isPresent()) {
            resp.setContentType("text/html");
            try (PrintWriter writer = resp.getWriter()) {
                writer.println("<!DOCTYPE html>");
                writer.println("<html>");
                writer.println("    <head>");
                writer.println("        <meta charset=\"UTF-8\">");
                writer.println("        <title>" + name + " | Request Headers</title>");
                writer.println("    </head>");
                writer.println("    <body>");
                writer.println("        <div>");
                writer.println("            <h1>Product founded</h1>");
                writer.println("            <h3>" + founded.get().getId() + "</h3>");
                writer.println("            <h3>" + founded.get().getName() + "</h3>");
                writer.println("            <h3>" + founded.get().getPrice() + "</h3>");
                writer.println("            <h3>" + founded.get().getCategory() + "</h3>");
                writer.println("        </div>");
                writer.println("    </body>");
                writer.println("/<html>");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product" + name + "not found");
        }
    }
}
