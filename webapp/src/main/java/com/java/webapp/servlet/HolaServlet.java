package com.java.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hola-servlet")
public class HolaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("      <head>");
        writer.println("          <meta charset=\"UTF-8\">");
        writer.println("          <title>Hola Servlet</title>");
        writer.println("      </head>");
        writer.println("      <body>");
        writer.println("          <h1>Hola Servlet!!!!</h1>");
        writer.println("      </body>");
        writer.println("</html>");
        writer.close();
    }
}
