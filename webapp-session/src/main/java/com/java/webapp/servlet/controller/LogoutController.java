package com.java.webapp.servlet.controller;

import com.java.webapp.servlet.service.LoginService;
import com.java.webapp.servlet.service.LoginServiceSessionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService loginService = new LoginServiceSessionImpl();
        Optional<String> username =loginService.getUsername(req);
        if(username.isPresent()) {
            HttpSession httpSession = req.getSession();
            httpSession.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
