package com.java.jdbc;

import com.java.jdbc.modelo.Producto;
import com.java.jdbc.repositorio.ProductoRepositorioImpl;
import com.java.jdbc.repositorio.Repositorio;
import com.java.jdbc.util.ConexionMySQL;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class EjemploJdbc {
    public static void main(String[] args) {
        try (Connection connection = ConexionMySQL.getConnection();) {
            Repositorio<Producto> productoRepositorio = new ProductoRepositorioImpl();

            listar(productoRepositorio.listar());

            obtener(productoRepositorio.obtener(1L));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void obtener(Producto producto) {
        System.out.println(producto);
    }

    private static void listar(List<Producto> productos) {
        productos.forEach(System.out::println);
    }
}
