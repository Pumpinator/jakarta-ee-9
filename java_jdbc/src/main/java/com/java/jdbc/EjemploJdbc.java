package com.java.jdbc;

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
    public static void main(String[] args) {
        modificar(new Producto(3, "Teclado Razer mecánico", 700, null));
        listar().forEach(producto -> {
            System.out.println(producto);
        });
    }

    public static Producto obtener(int id) {
        return productoRepositorio.obtener(id);
    }

    public static void crear(Producto producto) {
        productoRepositorio.guardar(producto);
    }

    public static void modificar(Producto producto) {
        productoRepositorio.guardar(producto);
    }

    private static List<Producto> listar() {;
        return productoRepositorio.listar();
    }
}
