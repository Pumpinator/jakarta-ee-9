package com.java.jdbc.repositorio;

import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T> {
    void guardar(T t) throws SQLException;

    T obtener(Integer id) throws SQLException;

    void eliminar(Integer id) throws SQLException;

    List<T> listar() throws SQLException;
}