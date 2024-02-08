package com.java.webapp.servlet.listener;

import com.java.webapp.servlet.model.Cart;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class ApplicationListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {
    private ServletContext servletContext;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContext = servletContextEvent.getServletContext();
        servletContext.log("Initializing webapp");
        servletContext.setAttribute("context key", "context value");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContext.log("Destroying webapp");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        servletContext.log("Initializing request");
        servletRequestEvent.getServletRequest().setAttribute("request key", "request value");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        servletContext.log("Destroying request");
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        servletContext.log("Creating HttpSession");
        httpSessionEvent.getSession().setAttribute("cart", new Cart());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        servletContext.log("Destroying HttpSession");
    }
}
