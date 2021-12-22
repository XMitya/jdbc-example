package ru.ifmo.pattern.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    void save(T t) throws SQLException;
    T get(Integer id) throws SQLException;
    List<T> getAll() throws SQLException;
    void remove(Integer id) throws SQLException;
}
