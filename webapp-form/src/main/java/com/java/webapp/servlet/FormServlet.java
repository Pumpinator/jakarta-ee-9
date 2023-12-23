package com.java.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@WebServlet("/form")
public class FormServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Integer id = Integer.valueOf(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String pais = req.getParameter("pais");
        String[] lenguajes = req.getParameterValues("lenguajes");
        String[] roles = req.getParameterValues("roles");
        String idioma = req.getParameter("idioma");
        Boolean habilitar = req.getParameter("habilitar") != null ? true : false;

        Map<String, String> errores = new HashMap<>();
        if (username == null || username.isBlank()) {
            errores.put("username", "Username es obligatorio");
        }
        if (password == null || password.isBlank()) {
            errores.put("password", "Password es obligatorio");
        }
        if (email == null || !email.contains("@")) {
            errores.put("email", "Email es obligatorio y debe ser válido");
        }
        if (pais == null || pais.isBlank()) {
            errores.put("pais", "País es obligatorio");
        }
        if (lenguajes == null || lenguajes.length == 0) {
            errores.put("lenguajes", "Lenguajes es obligatorio");
        }
        if (roles == null || roles.length == 0) {
            errores.put("roles", "Roles es obligatorio");
        }
        if (idioma == null) {
            errores.put("idioma", "Idioma es obligatorio");
        }

        if (errores.isEmpty()) {
            try (PrintWriter writer = resp.getWriter();) {
                writer.println("<!DOCTYPE html>");
                writer.println("<html>");
                writer.println("    <head>");
                writer.println("        <meta charset=\"UTF-8\">");
                writer.println("        <title>Formulario Respuesta</title>");
                writer.println("        <link rel=\"stylesheet\" href=\"webjars/bootstrap/5.3.2/css/bootstrap.min.css\">");
                writer.println("    </head>");
                writer.println("    <body>");
                writer.println("    <div class=\"container-fluid\">");
                writer.println("            <h3 class=\"text-center border-bottom border-dark\">Formulario Respuesta</h3>");
                writer.println("            <div class=\"p-3\">");
                writer.println("                <div class=\"container-fluid\">");
                writer.println("                    <div class=\"row mb-3\">");
                writer.println("                        <label class=\"form-label\">");
                writer.println("                        Id");
                writer.println("                        <div>");
                writer.println("                            <input class=\"col form-control\" type=\"text\" name=\"id\" value=" + id + " disabled>");
                writer.println("                        </div>");
                writer.println("                        </label>");
                writer.println("                    </div>");
                writer.println("                    <div class=\"row mb-3\">");
                writer.println("                        <label class=\"form-label\">");
                writer.println("                        Username");
                writer.println("                        <div>");
                writer.println("                            <input class=\"col form-control\" type=\"text\" name=\"username\" value=" + username + " disabled>");
                writer.println("                        </div>");
                writer.println("                        </label>");
                writer.println("                    </div>");
                writer.println("                    <div class=\"row mb-3\">");
                writer.println("                        <label class=\"form-label\">");
                writer.println("                        Password");
                writer.println("                        <div>");
                writer.println("                            <input class=\"col form-control\" type=\"text\" name=\"password\" value=" + password + " disabled>");
                writer.println("                        </div>");
                writer.println("                        </label>");
                writer.println("                    </div>");
                writer.println("                    <div class=\"row mb-3\">");
                writer.println("                        <label class=\"form-label\">");
                writer.println("                        Email");
                writer.println("                        <div>");
                writer.println("                            <input class=\"col form-control\" type=\"text\" name=\"email\" value=" + email + " disabled>");
                writer.println("                        </div>");
                writer.println("                        </label>");
                writer.println("                    </div>");
                writer.println("                    <div class=\"row mb-3\">");
                writer.println("                        <label class=\"form-label\">");
                writer.println("                        Pais");
                writer.println("                        <div>");
                writer.println("                            <select class=\"col form-select\" name=\"pais\" disabled>");
                writer.println("                                <option value=" + pais + " selected>" + pais + "</option>");
                writer.println("                            </select>");
                writer.println("                        </div>");
                writer.println("                        </label>");
                writer.println("                    </div>");
                writer.println("                    <div class=\"row mb-3\">");
                writer.println("                        <label class=\"form-label\">");
                writer.println("                        Lenguajes");
                writer.println("                        <div>");
                writer.println("                            <select class=\"col form-select\" name=\"pais\" multiple disabled>");
                Arrays.asList(lenguajes).forEach(lenguaje -> {
                    writer.println("                        <option value=" + lenguaje + " selected>" + lenguaje + "</option>");
                });
                writer.println("                            </select>");
                writer.println("                        </div>");
                writer.println("                        </label>");
                writer.println("                    </div>");
                writer.println("                    <div class=\"row mb-3\">");
                writer.println("                        <label class=\"col-auto form-label\">Roles</label>");
                writer.println("                        <div class=\"col-auto\">");
                Arrays.asList(roles).forEach(rol -> {
                    writer.println("                            <label class=\"form-check-label\">");
                    writer.println("                                " + rol);
                    writer.println("                                <input class=\"form-check\" type=\"checkbox\" name=\"roles\" value=" + rol + " checked disabled>");
                    writer.println("                            </label>");
                });
                writer.println("                        </div>");
                writer.println("                    </div>");
                writer.println("                    <div class=\"row mb-3\">");
                writer.println("                        <label class=\"form-label\">");
                writer.println("                        Idioma");
                writer.println("                        <div>");
                writer.println("                            <select class=\"col form-select\" name=\"idioma\" disabled>");
                writer.println("                                <option value=" + idioma + " selected>" + idioma + "</option>");
                writer.println("                            </select>");
                writer.println("                        </div>");
                writer.println("                        </label>");
                writer.println("                    </div>");
                writer.println("                    <div class=\"mb-3\">");
                writer.println("                        <label class=\"form-check-label\">");
                writer.println("                            Habilitar");
                writer.println("                            <input class=\"form-check\" type=\"checkbox\" name=\"habilitar\" " + (habilitar ? "checked" : "") + " disabled>");
                writer.println("                        </label");
                writer.println("                    </div>");
                writer.println("                </div>");
                writer.println("            </div>");
                writer.println("        </div>");
                writer.println("    </body>");
                writer.println("</html>");
            }
        } else {
//                writer.println("<p>PILA DE ERRORES</p>");
//                errores.forEach(error -> {
//                    writer.println("<li>" + error + "</li>");
//                });
//                writer.println("<p><a href=\"/webapp-form\">Volver atrás</a></p>");
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
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
