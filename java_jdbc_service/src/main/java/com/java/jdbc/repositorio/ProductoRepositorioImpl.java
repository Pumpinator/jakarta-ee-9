package com.java.jdbc.repositorio;

import com.java.jdbc.modelo.Categoria;
import com.java.jdbc.modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<Producto> {

    private Connection connection;

    public ProductoRepositorioImpl() {
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Producto guardar(Producto producto) throws SQLException {
        String query = (!esInsert(producto))
                ? "UPDATE productos SET nombre = ?, precio = ?, categoria_id = ?, sku = ? WHERE id = ?"
                : "INSERT INTO productos(nombre, precio, categoria_id, sku, fecha_registro) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setInt(2, producto.getPrecio());
            preparedStatement.setInt(3, producto.getCategoria().getId());
            preparedStatement.setString(4, producto.getSku());
            if (!esInsert(producto)) preparedStatement.setInt(5, producto.getId());
            preparedStatement.setDate(5, new Date(producto.getFechaRegistro().getTime()));
            preparedStatement.executeUpdate();
            if (esInsert(producto)) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) producto.setId(resultSet.getInt(1));
                }
            }
            return producto;
        }
    }

    @Override
    public Producto obtener(Integer id) throws SQLException {
        Producto producto = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.*, c.nombre categoria FROM productos p INNER JOIN categorias c ON p.categoria_id = c.id WHERE p.id = ?");) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    producto = crear(resultSet);
                }
            }
        }
        return producto;
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM productos WHERE id = ?");) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT p.*, c.nombre categoria FROM productos p INNER JOIN categorias c ON p.categoria_id = c.id");) {
            while (resultSet.next()) {
                productos.add(crear(resultSet));
            }
        }
        return productos;
    }

    private static Producto crear(ResultSet resultSet) throws SQLException {
        Producto producto = new Producto(
                resultSet.getInt("id"),
                resultSet.getString("nombre"),
                resultSet.getInt("precio"),
                resultSet.getDate("fecha_registro"),
                new Categoria(
                        resultSet.getInt("categoria_id"),
                        resultSet.getString("categoria")
                ),
                resultSet.getString("sku")
        );
        return producto;
    }

    private boolean esInsert(Producto producto) {
        if (producto.getId() == null) return true;
        return false;
    }
}
