package ru.ifmo.pattern.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlCriteria<T> {
    String toSql(Mapper<T> mapper);

    default void setArguments(PreparedStatement ps) throws SQLException {
        // No-op
    }
}
