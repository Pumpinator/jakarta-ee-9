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

        String cadena1 = req.getParameter("cadena1");
        String cadena2 = req.getParameter("cadena2");

        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("      <head>");
        writer.println("          <meta charset=\"UTF-8\">");
        writer.println("          <title>Parámetros GET</title>");
        writer.println("      </head>");
        writer.println("      <body>");
        writer.println("          <h1>Parámetros GET</h1>");
        if (cadena1 != null && cadena2 != null) {
            writer.println("          <h2>El parámetro de entrada1 es " + cadena1 + "</h2>");
            writer.println("          <h2>El parámetro de entrada2 es " + cadena2 + "</h2>");
        } else if (cadena1 != null) {
            writer.println("          <h2>El parámetro de entrada1 es " + cadena1 + "</h2>");
        } else if (cadena2 != null) {
            writer.println("          <h2>El parámetro de entrada2 es " + cadena2 + "</h2>");
        } else {
            writer.println("          <h2>No se han recibido parámetros de cadena</h2>");
        }
        try {
            Integer entero = Integer.valueOf(req.getParameter("entero"));
            writer.println("          <h2>El parámetro de entero es " + entero + "</h2>");
        } catch (NumberFormatException exception) {
            writer.println("          <h2>No se ha recibido parámetro de entero</h2>");
        }
        writer.println("      </body>");
        writer.println("</html>");
        writer.close();
    }
}
