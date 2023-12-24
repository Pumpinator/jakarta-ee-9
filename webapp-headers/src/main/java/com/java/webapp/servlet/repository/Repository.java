package com.java.webapp.servlet.repository;

import java.util.List;

public interface Repository<T> {

    List<T> findAll();

}
