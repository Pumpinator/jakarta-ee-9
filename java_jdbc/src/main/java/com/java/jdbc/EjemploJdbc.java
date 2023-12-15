package com.java.jdbc;

import java.sql.*;
import java.time.LocalDateTime;

public class EjemploJdbc {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jakartadb?serverTimezone=UTC";
        String username = "root";
        String password = "password";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM productos");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                int precio = resultSet.getInt("precio");
                Date fechaRegistro = resultSet.getDate("fecha_registro");
                System.out.println(id + "\t" + nombre + "\t" + precio + "\t" + fechaRegistro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
