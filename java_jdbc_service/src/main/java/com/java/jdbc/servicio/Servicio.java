package com.java.jdbc.servicio;

import com.java.jdbc.modelo.Categoria;
import com.java.jdbc.modelo.Producto;
import com.java.jdbc.repositorio.Repositorio;

import java.sql.SQLException;
import java.util.List;

public interface Servicio {
    Producto guardarProducto(Producto producto) throws SQLException;

    Producto obtenerProducto(int id) throws SQLException;

    void eliminarProducto(int id) throws SQLException;

    List<Producto> listarProductos() throws SQLException;

    Categoria guardarCategoria(Categoria categoria) throws SQLException;

    Categoria obtenerCategoria(int id) throws SQLException;

    void eliminarCategoria(int id) throws SQLException;

    List<Categoria> listarCategorias() throws SQLException;

    void guardarProductoConCategoria(Producto producto, Categoria categoria) throws SQLException;
}
