package com.java.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/form")
public class FormServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String pais = req.getParameter("pais");
        String[] lenguajes = req.getParameterValues("lenguajes");
        String[] roles = req.getParameterValues("roles");
        String idioma = req.getParameter("idioma");
        String habilitar = req.getParameter("habilitar");
        try (PrintWriter writer = resp.getWriter();) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("    <head>");
            writer.println("        <meta charset=\"UTF-8\">");
            writer.println("        <title>Formulario Respuesta</title>");
            writer.println("    </head>");
            writer.println("    <body>");
            writer.println("        <h1>Formulario Respuesta</h1>");
            writer.println("        <ul>");
            writer.println("            <li>Id " + id + "</li>");
            writer.println("        </ul>");
            writer.println("        <ul>");
            writer.println("            <li>Username " + username + "</li>");
            writer.println("        </ul>");
            writer.println("        <ul>");
            writer.println("            <li>Password " + password + "</li>");
            writer.println("        </ul>");
            writer.println("        <ul>");
            writer.println("            <li>Email " + email + "</li>");
            writer.println("        </ul>");
            writer.println("        <ul>");
            writer.println("            <li>País " + pais + "</li>");
            writer.println("        </ul>");
            writer.println("        <ul>");
            writer.println("            <li>Lenguajes ");
            writer.println("                <ul>");
            Arrays.asList(lenguajes).forEach(lenguaje -> {
                writer.println("                <li>" + lenguaje + "</li>");
            });
            writer.println("                </ul>");
            writer.println("            </li>");
            writer.println("        </ul>");
            writer.println("        <ul>");
            writer.println("            <li>Roles ");
            writer.println("                <ul>");
            Arrays.asList(roles).forEach(rol -> {
                writer.println("                    <li>" + rol + "</li>");
            });
            writer.println("                </ul>");
            writer.println("            </li>");
            writer.println("          </ul>");
            writer.println("        <ul>");
            writer.println("            <li>Idioma " + idioma + "</li>");
            writer.println("        </ul>");
            writer.println("        <ul>");
            writer.println("            <li>Habilitar " + habilitar + "</li>");
            writer.println("        </ul>");
            writer.println("    </body>");
            writer.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        try (PrintWriter writer = resp.getWriter();) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("      <head>");
            writer.println("          <meta charset=\"UTF-8\">");
            writer.println("          <title>Form Respuesta</title>");
            writer.println("      </head>");
            writer.println("      <body>");
            writer.println("          <h1>Form Respuesta</h1>");
            writer.println("          <ul>");
            writer.println("                <li>Username " + username + "</li>");
            writer.println("          </ul>");
            writer.println("          <ul>");
            writer.println("                <li>Password " + password + "</li>");
            writer.println("          </ul>");
            writer.println("          <ul>");
            writer.println("                <li>Email " + email + "</li>");
            writer.println("          </ul>");
            writer.println("      </body>");
            writer.println("</html>");
        }
    }
}
