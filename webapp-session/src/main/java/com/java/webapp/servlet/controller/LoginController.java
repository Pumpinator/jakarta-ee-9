package com.java.webapp.servlet.controller;

import com.java.webapp.servlet.service.LoginService;
import com.java.webapp.servlet.service.LoginServiceSessionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService loginService = new LoginServiceSessionImpl();
        Optional<String> username = loginService.getUsername(req);

        if (username.isPresent()) {
            resp.setContentType("text/html");
            try (PrintWriter writer = resp.getWriter()) {
                writer.println("<!DOCTYPE html>");
                writer.println("<html>");
                writer.println("    <head>");
                writer.println("        <meta charset=\"UTF-8\">");
                writer.println("        <title>" + username.get() + " | Webapp Cookies</title>");
                writer.println("    </head>");
                writer.println("    <body>");
                writer.println("        <div>");
                writer.println("            <h1>Welcome back, " + username.get() + "</h1>");
                writer.println("            <h3>You logged in before.</h3>");
                writer.println("            <p><a href='"+req.getContextPath()+"'>Go back...</a></p>");
                writer.println("            <p><a href='logout'>Logout</a></p>");
                writer.println("        </div>");
                writer.println("    </body>");
                writer.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("username", username);
            resp.sendRedirect("login");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not authorized");
        }
    }
}
