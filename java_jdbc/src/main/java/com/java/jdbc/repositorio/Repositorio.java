package com.java.jdbc.repositorio;

import java.util.List;

public interface Repositorio<T> {
    void guardar(T t);

    T obtener(Long id);

    void eliminar(Long id);

    List<T> listar();
}
