package com.java.jdbc.repositorio;

import com.java.jdbc.modelo.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorioImpl implements Repositorio<Categoria> {

    private Connection connection;

    public CategoriaRepositorioImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Categoria guardar(Categoria categoria) throws SQLException {
        String query = (!esInsert(categoria))
                ? "UPDATE categorias SET nombre = ? WHERE id = ?"
                : "INSERT INTO categorias(nombre) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, categoria.getNombre());
            if (!esInsert(categoria)) preparedStatement.setInt(2, categoria.getId());
            preparedStatement.executeUpdate();
            if (esInsert(categoria)) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
                    if (resultSet.next()) categoria.setId(resultSet.getInt(1));
                }
            }
        }
        return categoria;
    }

    @Override
    public Categoria obtener(Integer id) throws SQLException {
        Categoria categoria = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categorias WHERE id = ?");) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    categoria = crear(resultSet);
                }
            }
        }
        return categoria;
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categorias WHERE id = ?");) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM categorias");) {
            while (resultSet.next()) {
                categorias.add(crear(resultSet));
            }
        }
        return categorias;
    }

    private Categoria crear(ResultSet resultSet) throws SQLException {
        Categoria categoria = new Categoria(
                resultSet.getInt("id"),
                resultSet.getString("nombre")
        );
        return categoria;
    }

    private boolean esInsert(Categoria categoria) {
        if (categoria.getId() == null) return true;
        return false;
    }
}
