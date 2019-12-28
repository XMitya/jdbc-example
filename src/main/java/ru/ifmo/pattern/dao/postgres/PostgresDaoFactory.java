package ru.ifmo.pattern.dao.postgres;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import ru.ifmo.pattern.dao.AbstractDaoFactory;
import ru.ifmo.pattern.dao.CourseDao;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

public class PostgresDaoFactory extends AbstractDaoFactory {
    private ComboPooledDataSource cpds;

    @Override
    public CourseDao createCourseDao() throws SQLException {
        return new PostgresCourseDao(cpds.getConnection());
    }

    @Override
    public void connect() throws SQLException {
        // Допустим, эти параметры берутся тоже из системных переменных.
        try {
            cpds = new ComboPooledDataSource();

            cpds.setDriverClass("org.postgresql.Driver");
            cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/ifmo");
            cpds.setUser("ifmo");
            cpds.setPassword("q1w2e3");

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
    }

}
