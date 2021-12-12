package ru.ifmo.pattern.repository;

import ru.ifmo.pattern.repository.postgres.PostgresRepositoryFactory;

import java.sql.SQLException;

public abstract class AbstractRepositoryFactory {
    private static AbstractRepositoryFactory factory;

    public synchronized static AbstractRepositoryFactory getFactory() {
        if (factory == null) {
            factory = new PostgresRepositoryFactory();
        }

        return factory;
    }

    public abstract <T> Repository<T> createRepository(Class<T> entityType) throws SQLException;
    public abstract void connect(String host, String dbName, String username, String password) throws SQLException;

    public void close() throws SQLException {
        _close();

        factory = null;
    }

    protected abstract void _close() throws SQLException;
}
