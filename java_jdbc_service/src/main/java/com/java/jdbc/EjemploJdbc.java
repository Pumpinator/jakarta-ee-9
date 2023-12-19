package com.java.jdbc;

import com.java.jdbc.modelo.Categoria;
import com.java.jdbc.modelo.Producto;
import com.java.jdbc.repositorio.CategoriaRepositorioImpl;
import com.java.jdbc.repositorio.ProductoRepositorioImpl;
import com.java.jdbc.repositorio.Repositorio;
import com.java.jdbc.servicio.CatalogoServicio;
import com.java.jdbc.util.ConexionMySQL;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class EjemploJdbc {
    public static void main(String[] args) throws SQLException {
        CatalogoServicio catalogoServicio = new CatalogoServicio();

        catalogoServicio.listarProductos().forEach(System.out::println);
        catalogoServicio.listarCategorias().forEach(System.out::println);

        catalogoServicio.guardarProductoConCategoria(
                new Producto(
                        null,
                        "Lampara LED escritorio",
                        990,
                        new Date(),
                        null,
                        "abcdefgh12"
                ),
                new Categoria(
                        null,
                        "Iluminación"
                )
        );

        catalogoServicio.listarProductos().forEach(System.out::println);
        catalogoServicio.listarCategorias().forEach(System.out::println);
    }
}
