package com.java.webapp.servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "password";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            resp.setContentType("text/html");
            try (PrintWriter writer = resp.getWriter()) {
                writer.println("<!DOCTYPE html>");
                writer.println("<html>");
                writer.println("    <head>");
                writer.println("        <meta charset=\"UTF-8\">");
                writer.println("        <title>" + username + " | Request Headers</title>");
                writer.println("    </head>");
                writer.println("    <body>");
                writer.println("        <div>");
                writer.println("            <h1>Logged in</h1>");
                writer.println("            <h3>Welcome, " + username + "</h3>");
                writer.println("        </div>");
                writer.println("    </body>");
                writer.println("/<html>");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not authorized");
        }
    }
}
