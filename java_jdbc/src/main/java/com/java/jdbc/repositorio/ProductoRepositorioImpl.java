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

    }

    @Override
    public Producto obtener(Long id) {
        Producto producto = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM productos WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                producto = crear(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public void eliminar(Long id) {

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
        producto.setId(resultSet.getInt("id"));
        producto.setNombre(resultSet.getString("nombre"));
        producto.setPrecio(resultSet.getInt("precio"));
        producto.setFechaRegistro(resultSet.getDate("fecha_registro"));
        return producto;
    }
}
