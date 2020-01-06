package ru.ifmo.pattern.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Mapper<T> {
    List<String> fieldNames();
    String columns();
    String tableName();
    List<T> toEntities(ResultSet rs) throws SQLException;
    T toEntity(ResultSet rs) throws SQLException;
    int id(T entity);
    void setValues(PreparedStatement ps, T entity);
}
