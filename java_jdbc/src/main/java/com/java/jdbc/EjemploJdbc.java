package com.java.jdbc;

import com.java.jdbc.modelo.Producto;
import com.java.jdbc.repositorio.ProductoRepositorioImpl;
import com.java.jdbc.repositorio.Repositorio;
import com.java.jdbc.util.ConexionMySQL;

import java.sql.*;
import java.time.LocalDateTime;

public class EjemploJdbc {
    public static void main(String[] args) {
        try (Connection connection = ConexionMySQL.getConnection();) {
            Repositorio<Producto> productoRepositorio = new ProductoRepositorioImpl();
            productoRepositorio.listar().forEach(producto -> {
                System.out.println(producto.getId() + "\t" + producto.getNombre() + "\t" + producto.getPrecio() + "\t" + producto.getFechaRegistro());
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
