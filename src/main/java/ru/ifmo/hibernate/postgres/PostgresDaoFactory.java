package ru.ifmo.hibernate.postgres;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import ru.ifmo.hibernate.AbstractDaoFactory;
import ru.ifmo.hibernate.CourseDao;
import ru.ifmo.hibernate.CourseDaoImpl;
import ru.ifmo.hibernate.entity.CourseEntity;
import ru.ifmo.hibernate.entity.GradeEntity;
import ru.ifmo.hibernate.entity.StudentEntity;
import ru.ifmo.hibernate.entity.TeacherEntity;

import java.util.Properties;

public class PostgresDaoFactory extends AbstractDaoFactory {
    private SessionFactory sessionFactory;

    @Override
    public CourseDao createCourseDao() {
        return new CourseDaoImpl(sessionFactory);
    }

    @Override
    public void connect() {
        if (sessionFactory != null)
            return;

        Configuration configuration = new Configuration();

        // Hibernate settings equivalent to hibernate.cfg.xml's properties

        Properties settings = new Properties();

        settings.put(Environment.DRIVER, "org.postgresql.Driver");
        settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/ifmo");
        settings.put(Environment.USER, "ifmo");
        settings.put(Environment.PASS, "q1w2e3");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "validate");

        configuration.setProperties(settings);

        configuration.addAnnotatedClass(CourseEntity.class);
        configuration.addAnnotatedClass(TeacherEntity.class);
        configuration.addAnnotatedClass(StudentEntity.class);
        configuration.addAnnotatedClass(GradeEntity.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    protected void _close() {
        sessionFactory.close();
        sessionFactory = null;
    }
}
