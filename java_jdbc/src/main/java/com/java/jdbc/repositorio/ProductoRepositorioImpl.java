package com.java.jdbc.repositorio;

import com.java.jdbc.modelo.Categoria;
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
                ? "UPDATE productos SET nombre = ?, precio = ?, categoria_id = ? WHERE id = ?"
                : "INSERT INTO productos(nombre, precio, categoria_id, fecha_registro) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query);) {
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setInt(2, producto.getPrecio());
            preparedStatement.setInt(3, producto.getCategoria().getId());
            if (producto.getId() != null && producto.getId() > 0) {
                preparedStatement.setInt(4, producto.getId());
            } else {
                preparedStatement.setDate(4, new Date(producto.getFechaRegistro().getTime()));
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto obtener(Integer id) {
        Producto producto = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT p.*, c.nombre categoria FROM productos p INNER JOIN categorias c ON p.categoria_id = c.id WHERE id = ?")) {
            preparedStatement.setInt(1, id);
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
    public void eliminar(Integer id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM productos WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();

        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT p.*, c.nombre categoria FROM productos p INNER JOIN categorias c ON p.categoria_id = c.id")) {
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
        Producto producto = new Producto(
                resultSet.getInt("id"),
                resultSet.getString("nombre"),
                resultSet.getInt("precio"),
                resultSet.getDate("fecha_registro")
        );
        Categoria categoria = new Categoria(
                resultSet.getInt("categoria_id"),
                resultSet.getString("categoria")
        );
        producto.setCategoria(categoria);
        return producto;
    }
}
