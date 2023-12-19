package com.java.jdbc.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private static String url = "jdbc:mysql://localhost:3306/jakartadb?serverTimezone=UTC";
    private static String username = "root";
    private static String password = "password";
    private static BasicDataSource pool;

    public static BasicDataSource getPool() throws SQLException {
        if(pool == null) {
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(username);
            pool.setPassword(password);
            pool.setInitialSize(3);
            pool.setMinIdle(3);
            pool.setMaxIdle(8);
            pool.setMaxTotal(8);
        }
        return pool;
    }

    public static Connection getConnection() throws SQLException {
        return getPool().getConnection();
    }
}
