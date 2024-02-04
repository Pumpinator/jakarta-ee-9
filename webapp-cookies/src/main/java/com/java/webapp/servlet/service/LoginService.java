package com.java.webapp.servlet.service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface LoginService {
    public Optional<String> getUsername(HttpServletRequest req);
}
