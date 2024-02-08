package com.java.webapp.servlet.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

}
