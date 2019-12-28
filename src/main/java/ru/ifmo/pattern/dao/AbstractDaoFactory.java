package ru.ifmo.pattern.dao;

import ru.ifmo.pattern.dao.postgres.PostgresDaoFactory;

import java.sql.SQLException;

public abstract class AbstractDaoFactory {
    private static AbstractDaoFactory factory;

    public synchronized static AbstractDaoFactory getDaoFactory() {
        if (factory == null)
            factory = createFactory();

        return factory;
    }

    private static AbstractDaoFactory createFactory() {
        // Здесь может проверяться системная переменная на выбор
        // конкретной реализации фабрики.
        return new PostgresDaoFactory();
    }

    public abstract CourseDao createCourseDao() throws SQLException;
    public abstract void connect() throws SQLException;

    public void close() throws SQLException {
        _close();

        factory = null;
    }

    protected abstract void _close() throws SQLException;
}
