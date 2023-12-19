package com.java.jdbc;

import com.java.jdbc.modelo.Categoria;
import com.java.jdbc.modelo.Producto;
import com.java.jdbc.repositorio.CategoriaRepositorioImpl;
import com.java.jdbc.repositorio.ProductoRepositorioImpl;
import com.java.jdbc.repositorio.Repositorio;
import com.java.jdbc.util.ConexionMySQL;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class EjemploJdbc {
    private static Repositorio<Producto> productoRepositorio;
    private static Repositorio<Categoria> categoriaRepositorio;

    public static void main(String[] args) throws SQLException {
        try (Connection connection = ConexionMySQL.getConnection();) {
            if (connection.getAutoCommit()) connection.setAutoCommit(false);
            try {
                categoriaRepositorio = new CategoriaRepositorioImpl(connection);
                productoRepositorio = new ProductoRepositorioImpl(connection);

                Categoria nuevaCategoria = crearCategoria(new Categoria(
                        null,
                        "Electrohogar"
                ));

                System.out.println("Nueva categoria -> " + nuevaCategoria + "\n");
                listarCategorias().forEach(System.out::println);
                listarProductos().forEach(System.out::println);

                Producto nuevoProducto = crearProducto(new Producto(
                        null,
                        "Refrigerador Samsung",
                        9900,
                        new Date(),
                        nuevaCategoria,
                        "abcdefg123"
                ));

                System.out.println("Nuevo producto -> " + nuevoProducto + "\n");
                listarProductos().forEach(System.out::println);
                listarCategorias().forEach(System.out::println);

                connection.commit();
            } catch (SQLException exception) {
                connection.rollback();
                exception.printStackTrace();
            }
        }
    }

    public static Producto obtenerProducto(int id) throws SQLException {
        return productoRepositorio.obtener(id);
    }

    public static Producto crearProducto(Producto producto) throws SQLException {
        return productoRepositorio.guardar(producto);
    }

    public static Producto modificarProducto(Producto producto) throws SQLException {
        return productoRepositorio.guardar(producto);
    }

    public static void eliminarProducto(int id) throws SQLException {
        productoRepositorio.eliminar(id);
    }

    private static List<Producto> listarProductos() throws SQLException {
        return productoRepositorio.listar();
    }

    public static Categoria obtenerCategoria(int id) throws SQLException {
        return categoriaRepositorio.obtener(id);
    }

    public static Categoria crearCategoria(Categoria categoria) throws SQLException {
        return categoriaRepositorio.guardar(categoria);
    }

    public static Categoria modificarCategoria(Categoria categoria) throws SQLException {
        return categoriaRepositorio.guardar(categoria);
    }

    public static void eliminarCategoria(int id) throws SQLException {
        categoriaRepositorio.eliminar(id);
    }

    private static List<Categoria> listarCategorias() throws SQLException {
        return categoriaRepositorio.listar();
    }
}
