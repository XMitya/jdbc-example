package ru.ifmo.hibernate;

import ru.ifmo.hibernate.postgres.PostgresDaoFactory;

public abstract class AbstractDaoFactory {
    private static AbstractDaoFactory factory;

    public synchronized static AbstractDaoFactory factory() {
        if (factory == null) {
            factory = new PostgresDaoFactory();
        }

        return factory;
    }

    public abstract CourseDao createCourseDao();
    public abstract void connect(String host, String dbName, String username, String password);

    public void close() {
        _close();

        factory = null;
    }

    protected abstract void _close();
}
