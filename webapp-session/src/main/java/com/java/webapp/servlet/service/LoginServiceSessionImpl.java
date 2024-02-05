package com.java.webapp.servlet.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceSessionImpl implements  LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        HttpSession httpSession = req.getSession();
        String username = (String) httpSession.getAttribute("username");
        if (username != null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }
}
