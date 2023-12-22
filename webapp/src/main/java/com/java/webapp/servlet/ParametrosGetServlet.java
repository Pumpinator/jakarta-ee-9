package com.java.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/parametros/get")
public class ParametrosGetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String entrada = req.getParameter("entrada");
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("      <head>");
        writer.println("          <meta charset=\"UTF-8\">");
        writer.println("          <title>Parámetros GET</title>");
        writer.println("      </head>");
        writer.println("      <body>");
        writer.println("          <h1>Parámetros GET</h1>");
        writer.println("          <h2>El parametro de entrada es " + entrada + "</h2>");
        writer.println("      </body>");
        writer.println("</html>");
        writer.close();
    }
}
