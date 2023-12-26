package com.java.webapp.servlet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.webapp.servlet.model.Product;
import com.java.webapp.servlet.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/products", "/products/excel", "/products/json"})
public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductService();
        List<Product> products = productService.getAll();

        resp.setContentType("text/html;charset=UTF-8");
        boolean isExcel = req.getServletPath().endsWith("excel");
        boolean isJson = req.getServletPath().endsWith("json");

        if (isExcel) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=products.xls");
        } else if (isJson) {
            resp.setContentType("application/json");
        }

        try (PrintWriter writer = resp.getWriter();) {
            if (!isJson) {
                if (!isExcel) {
                    writer.println("<!DOCTYPE html>");
                    writer.println("<html>");
                    writer.println("    <head>");
                    writer.println("        <meta charset=\"UTF-8\">");
                    writer.println("        <title>Products | Request Headers</title>");
                    writer.println("    </head>");
                    writer.println("    <body>");
                    writer.println("        <div>");
                    writer.println("            <h1>Products | Request Headers</h1>");
                }
                writer.println("            <table>");
                writer.println("                <thead>");
                writer.println("                    <tr>");
                writer.println("                        <th>ID</th>");
                writer.println("                        <th>NAME</th>");
                writer.println("                        <th>CATEGORY</th>");
                writer.println("                        <th>PRICE</th>");
                writer.println("                    </tr>");
                writer.println("                </thead>");
                writer.println("                <tbody>");
                products.forEach(product -> {
                    writer.println("                    <tr>");
                    writer.println("<td>" + product.getId() + "</td>");
                    writer.println("<td>" + product.getName() + "</td>");
                    writer.println("<td>" + product.getCategory() + "</td>");
                    writer.println("<td>" + product.getPrice() + "</td>");
                    writer.println("                    </tr>");
                });
                writer.println("                </tbody>");
                writer.println("            </table>");
                if (!isExcel) {
                    writer.println("            <a href=\"products/excel\">Export XLS</a>");
                    writer.println("            <a href=\"products/json\">Export JSON</a>");
                    writer.println("            <a href=\"/webapp-headers\"}>Go back...</a>");
                    writer.println("        </div>");
                    writer.println("      </body>");
                    writer.println("</html>");
                }
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(products);
                writer.write(json);
            }
        }
    }
}
