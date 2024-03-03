package com.java.webapp.servlet.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {

    List<T> findAll() throws SQLException;

    T findById(Long id) throws SQLException;
    void save(T t) throws SQLException;
    boolean deleteById(Long id) throws SQLException;

    boolean isUpdating(T t);
}
