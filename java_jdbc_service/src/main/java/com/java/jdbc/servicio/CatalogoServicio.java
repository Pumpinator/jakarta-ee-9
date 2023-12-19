package com.java.jdbc.servicio;

import com.java.jdbc.modelo.Categoria;
import com.java.jdbc.modelo.Producto;
import com.java.jdbc.repositorio.CategoriaRepositorioImpl;
import com.java.jdbc.repositorio.ProductoRepositorioImpl;
import com.java.jdbc.repositorio.Repositorio;
import com.java.jdbc.util.ConexionMySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CatalogoServicio implements Servicio {
    private Repositorio<Producto> productoRepositorio;
    private Repositorio<Categoria> categoriaRepositorio;

    public CatalogoServicio() {
        this.productoRepositorio = new ProductoRepositorioImpl();
        this.categoriaRepositorio = new CategoriaRepositorioImpl();
    }

    @Override
    public Producto guardarProducto(Producto producto) throws SQLException {
        try (Connection connection = ConexionMySQL.getConnection();) {
            productoRepositorio.setConnection(connection);
            if (connection.getAutoCommit()) connection.setAutoCommit(false);
            try {
                producto = productoRepositorio.guardar(producto);
                connection.commit();
            } catch (SQLException exception) {
                connection.rollback();
                exception.printStackTrace();
            }
            return producto;
        }
    }

    @Override
    public Producto obtenerProducto(int id) throws SQLException {
        try (Connection connection = ConexionMySQL.getConnection();) {
            productoRepositorio.setConnection(connection);
            return productoRepositorio.obtener(id);
        }
    }

    @Override
    public void eliminarProducto(int id) throws SQLException {
        try (Connection connection = ConexionMySQL.getConnection();) {
            productoRepositorio.setConnection(connection);
            if (connection.getAutoCommit()) connection.setAutoCommit(false);
            try {
                productoRepositorio.eliminar(id);
                connection.commit();
            } catch (SQLException exception) {
                connection.rollback();
                exception.printStackTrace();
            }
        }
    }

    @Override
    public List<Producto> listarProductos() throws SQLException {
        try (Connection connection = ConexionMySQL.getConnection();) {
            productoRepositorio.setConnection(connection);
            return productoRepositorio.listar();
        }
    }

    @Override
    public Categoria guardarCategoria(Categoria categoria) throws SQLException {
        try (Connection connection = ConexionMySQL.getConnection();) {
            categoriaRepositorio.setConnection(connection);
            if (connection.getAutoCommit()) connection.setAutoCommit(false);
            try {
                categoria = categoriaRepositorio.guardar(categoria);
                connection.commit();
            } catch (SQLException exception) {
                connection.rollback();
                exception.printStackTrace();
            }
            return categoria;
        }
    }

    @Override
    public Categoria obtenerCategoria(int id) throws SQLException {
        try (Connection connection = ConexionMySQL.getConnection();) {
            categoriaRepositorio.setConnection(connection);
            return categoriaRepositorio.obtener(id);
        }
    }

    @Override
    public void eliminarCategoria(int id) throws SQLException {
        try (Connection connection = ConexionMySQL.getConnection();) {
            categoriaRepositorio.setConnection(connection);
            if (connection.getAutoCommit()) connection.setAutoCommit(false);
            try {
                categoriaRepositorio.eliminar(id);
                connection.commit();
            } catch (SQLException exception) {
                connection.rollback();
                exception.printStackTrace();
            }
        }

    }

    @Override
    public List<Categoria> listarCategorias() throws SQLException {
        try (Connection connection = ConexionMySQL.getConnection();) {
            categoriaRepositorio.setConnection(connection);
            return categoriaRepositorio.listar();
        }
    }

    @Override
    public void guardarProductoConCategoria(Producto producto, Categoria categoria) throws SQLException {
        try (Connection connection = ConexionMySQL.getConnection();) {
            productoRepositorio.setConnection(connection);
            categoriaRepositorio.setConnection(connection);
            if (connection.getAutoCommit()) connection.setAutoCommit(false);
            try {
                categoria = categoriaRepositorio.guardar(categoria);
                producto.setCategoria(categoria);
                producto = productoRepositorio.guardar(producto);
                connection.commit();
            } catch (SQLException exception) {
                connection.rollback();
                exception.printStackTrace();
            }
        }

    }
}
