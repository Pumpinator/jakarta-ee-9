package com.java.webapp.servlet.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static String url = "jdbc:mysql://localhost:3306/jakartadb?serverTimezone=America/Mexico_City";
    private static String user = "root";
    private static String password = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
