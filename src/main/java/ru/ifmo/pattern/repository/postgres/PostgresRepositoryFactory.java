package ru.ifmo.pattern.repository.postgres;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import ru.ifmo.pattern.entity.CourseEntity;
import ru.ifmo.pattern.repository.AbstractRepositoryFactory;
import ru.ifmo.pattern.repository.Repository;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

public class PostgresRepositoryFactory extends AbstractRepositoryFactory {
    private ComboPooledDataSource cpds;

    @SuppressWarnings("unchecked")
    @Override
    public <T> Repository<T> createRepository(Class<T> entityType) throws SQLException {
        if (CourseEntity.class.equals(entityType)) {
            return (Repository<T>) new PostgresCourseRepository(new CourseMapper(), cpds.getConnection());
        }

        throw new IllegalArgumentException("Unsupported entity type: " + entityType + ". Available types: " + CourseEntity.class);
    }

    @Override
    public void connect(String host, String dbName, String username, String password) throws SQLException {
        if (cpds != null)
            return;

        try {
            cpds = new ComboPooledDataSource();

            cpds.setDriverClass("org.postgresql.Driver");
            cpds.setJdbcUrl("jdbc:postgresql://%s/%s".formatted(host, dbName));
            cpds.setUser(username);
            cpds.setPassword(password);

            cpds.setMinPoolSize(1);
            cpds.setMaxPoolSize(5);
            cpds.setMaxStatements(180);
        } catch (PropertyVetoException e) {
            throw new SQLException(e);
        }
    }

    @Override
    protected void _close() {
        cpds.close();
        cpds = null;
    }
}
