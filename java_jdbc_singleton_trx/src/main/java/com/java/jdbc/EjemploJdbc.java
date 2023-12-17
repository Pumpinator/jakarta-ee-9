package com.java.jdbc;

import com.java.jdbc.modelo.Categoria;
import com.java.jdbc.modelo.Producto;
import com.java.jdbc.repositorio.ProductoRepositorioImpl;
import com.java.jdbc.repositorio.Repositorio;
import com.java.jdbc.util.ConexionMySQL;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class EjemploJdbc {
    static Repositorio<Producto> productoRepositorio = new ProductoRepositorioImpl();

    public static void main(String[] args) throws SQLException {
        try (Connection connection = ConexionMySQL.getConnection()) {
            if (connection.getAutoCommit()) connection.setAutoCommit(false);
            try {
                crear(new Producto(
                        null,
                        "Teclado IBM mecánico",
                        550,
                        new Date(),
                        new Categoria(3, null),
                        "abcde12345"
                ));
                modificar(new Producto(
                        5,
                        "Teclado Corsair K95 mecanico",
                        1000,
                        null,
                        new Categoria(2, null),
                        "abcdef1234"
                ));
                listar().forEach(producto -> {
                    System.out.println(producto);
                });
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        }
    }

    public static Producto obtener(int id) throws SQLException {
        return productoRepositorio.obtener(id);
    }

    public static void crear(Producto producto) throws SQLException {
        productoRepositorio.guardar(producto);
    }

    public static void modificar(Producto producto) throws SQLException {
        productoRepositorio.guardar(producto);
    }

    public static void eliminar(int id) throws SQLException {
        productoRepositorio.eliminar(id);
    }

    private static List<Producto> listar() throws SQLException {
        return productoRepositorio.listar();
    }
}
