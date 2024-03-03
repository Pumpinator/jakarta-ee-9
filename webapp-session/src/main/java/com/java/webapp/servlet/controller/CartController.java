package com.java.webapp.servlet.controller;

import com.java.webapp.servlet.model.Cart;
import com.java.webapp.servlet.model.Item;
import com.java.webapp.servlet.model.Product;
import com.java.webapp.servlet.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

@WebServlet({"/cart", "/cart/add", "/cart/update"})
public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.endsWith("add")) {
            addItem(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/cart.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.endsWith("update")) {
            updateCart(req, resp);
        }
    }

    private void updateCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("cart") != null) {
            Cart cart = (Cart) session.getAttribute("cart");
            updateItems(req, cart);
            updateQuantity(req, cart);
        }

        resp.sendRedirect(req.getContextPath() + "/cart");
    }

    private static void addItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ProductService productService = new ProductService(connection);
        Long id = Long.parseLong(req.getParameter("id"));
        Optional<Product> product = productService.getById(id);
        if (product.isPresent()) {
            HttpSession httpSession = req.getSession();
            Item item = new Item(1, product.get());
            Cart cart = (Cart) httpSession.getAttribute("cart");
            cart.addItem(item);
        }
        resp.sendRedirect(req.getContextPath() + "/cart");
    }

    private void updateQuantity(HttpServletRequest req, Cart cart) {
        Enumeration<String> enumeration = req.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String param = enumeration.nextElement();
            if (param.startsWith("quantity_")) {
                String id = param.substring(9);
                String quantiity = req.getParameter(param);
                if (quantiity != null) cart.updateQuantity(id, Integer.parseInt(quantiity));

            }
        }
    }

    private void updateItems(HttpServletRequest req, Cart cart) {
        String[] deletedIds = req.getParameterValues("updateItems");
        if (deletedIds != null && deletedIds.length > 0) {
            List<String> ids = Arrays.asList(deletedIds);
            cart.removeProducts(ids);
        }
    }
}
