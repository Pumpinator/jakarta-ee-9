package com.java.webapp.servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/time")
public class TimeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setHeader("refresh", "1");
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        try (PrintWriter writer = resp.getWriter();) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("    <head>");
            writer.println("        <meta charset=\"UTF-8\">");
            writer.println("        <title>Time | GET</title>");
            writer.println("    </head>");
            writer.println("    <body>");
            writer.println("        <div>");
            writer.println("            <h1>Time | GET</h1>");
            writer.println("            <h3>" + localTime.format(dateTimeFormatter) + "</h3>");
            writer.println("            <a href=\"/webapp-headers\"}>Go back...</a>");
            writer.println("        </div>");
            writer.println("    </body>");
            writer.println("</html>");
        }
    }
}
