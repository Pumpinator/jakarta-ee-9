package com.java.jdbc;

import com.java.jdbc.util.ConexionMySQL;

import java.sql.*;
import java.time.LocalDateTime;

public class EjemploJdbc {
    public static void main(String[] args) {

        try (Connection connection = ConexionMySQL.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM productos");) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                int precio = resultSet.getInt("precio");
                Date fechaRegistro = resultSet.getDate("fecha_registro");
                System.out.println(id + "\t" + nombre + "\t" + precio + "\t" + fechaRegistro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
