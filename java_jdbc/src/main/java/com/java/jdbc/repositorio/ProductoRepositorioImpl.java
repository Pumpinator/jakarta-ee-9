package com.java.jdbc.repositorio;

import com.java.jdbc.modelo.Producto;
import com.java.jdbc.util.ConexionMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<Producto> {

    private Connection getConnection() throws SQLException {
        return ConexionMySQL.getConnection();
    }

    @Override
    public void guardar(Producto producto) {
        String query = (producto.getId() != null && producto.getId() > 0)
                ? "UPDATE productos SET nombre = ?, precio = ?, fecha_registro = ?"
                : "INSERT INTO productos(nombre, precio, fecha_registro) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query);) {
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setLong(2, producto.getPrecio());
            if (producto.getId() != null && producto.getId() > 0) {
                preparedStatement.setLong(3, producto.getId());
            } else {
                preparedStatement.setDate(3, new Date(producto.getFechaRegistro().getTime()));
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto obtener(Long id) {
        Producto producto = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM productos WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    producto = crear(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public void eliminar(Long id) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM productos WHERE id = ?")) {
            preparedStatement.setLong(0, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();

        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM productos")) {
            while (resultSet.next()) {
                Producto producto = crear(resultSet);
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    private static Producto crear(ResultSet resultSet) throws SQLException {
        Producto producto = new Producto();
        producto.setId(resultSet.getLong("id"));
        producto.setNombre(resultSet.getString("nombre"));
        producto.setPrecio(resultSet.getInt("precio"));
        producto.setFechaRegistro(resultSet.getDate("fecha_registro"));
        return producto;
    }
}
