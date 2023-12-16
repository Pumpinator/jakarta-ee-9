package com.java.jdbc.repositorio;

import java.util.List;

public interface Repositorio<T> {
    void guardar(T t);

    T obtener(Integer id);

    void eliminar(Integer id);

    List<T> listar();
}