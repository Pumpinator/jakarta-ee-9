package com.java.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private static String url = "jdbc:mysql://localhost:3306/jakartadb?serverTimezone=UTC";
    private static String username = "root";
    private static String password = "password";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
