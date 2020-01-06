package ru.ifmo.pattern.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    void save(T entity);
    void delete(int id);
    List<T> query(SqlCriteria<T> criteria) throws SQLException;
}
