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
import java.util.Optional;

@WebServlet({"/cart", "/cart/add"})
public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().endsWith("add")) {
            ProductService productService = new ProductService();
            Long id = Long.parseLong(req.getParameter("id"));
            Optional<Product> product = productService.getById(id);
            if (product.isPresent()) {
                HttpSession httpSession = req.getSession();
                Item item = new Item(1, product.get());
                Cart cart;
                if (httpSession.getAttribute("cart") == null) {
                    cart = new Cart();
                    httpSession.setAttribute("cart", cart);
                } else {
                    cart = (Cart) httpSession.getAttribute("cart");
                }
                cart.addItem(item);
            }
            resp.sendRedirect(req.getContextPath() + "/cart");
        } else {
            getServletContext().getRequestDispatcher("/cart.jsp").forward(req, resp);
        }
    }
}
