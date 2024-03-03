package com.java.webapp.servlet.filter;

import com.java.webapp.servlet.exception.ConnectionException;
import com.java.webapp.servlet.util.DatabaseUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConnectionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try (Connection connection = DatabaseUtil.getConnection()) {
            if (connection.getAutoCommit()) connection.setAutoCommit(false);
            try {
                request.setAttribute("connection", connection);
                chain.doFilter(request, response);
                connection.commit();
            } catch (SQLException | ConnectionException e) {
                connection.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
