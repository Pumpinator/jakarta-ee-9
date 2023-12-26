package com.java.webapp.servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/request")
public class RequestController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String method = req.getMethod();
        String requestURI = req.getRequestURI();
        String requestURL = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String localAddress = req.getLocalAddr();
        Integer localPort = req.getLocalPort();
        String scheme = req.getScheme();
        String host = req.getHeader("host");
        String remoteAddress = req.getRemoteAddr();
        String serverName = req.getServerName();

        Enumeration<String> headerNames = req.getHeaderNames();
        try (PrintWriter writer = resp.getWriter();) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("    <head>");
            writer.println("        <meta charset=\"UTF-8\">");
            writer.println("        <title>GET | Request Headers</title>");
            writer.println("    </head>");
            writer.println("    <body>");
            writer.println("        <div>");
            writer.println("            <h2>GET | Request Common Headers</h2>");
            writer.println("            <ul>");
            writer.println("                <li>Method: " + method + "</li>");
            writer.println("                <li>Request URI: " + requestURI + "</li>");
            writer.println("                <li>Request URL: " + requestURL + "</li>");
            writer.println("                <li>Context Path: " + contextPath + "</li>");
            writer.println("                <li>Servlet Path: " + servletPath + "</li>");
            writer.println("                <li>Local Address: " + localAddress + "</li>");
            writer.println("                <li>Local Port: " + localPort + "</li>");
            writer.println("                <li>Scheme: " + scheme + "</li>");
            writer.println("                <li>Host: " + host + "</li>");
            writer.println("                <li>Remote Address: " + remoteAddress + "</li>");
            writer.println("                <li>Server Name: " + serverName + "</li>");
            writer.println("            </ul>");
            writer.println("            <h2>GET | Request All Headers</h2>");
            writer.println("            <ul>");
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                writer.println("            <li>" + headerName + ": " + req.getHeader(headerName) + "</li>");
            }
            writer.println("            </ul>");
            writer.println("            <a href=\"/webapp-headers\"}>Go back...</a>");
            writer.println("        </div>");
            writer.println("      </body>");
            writer.println("</html>");
        }
    }
}
