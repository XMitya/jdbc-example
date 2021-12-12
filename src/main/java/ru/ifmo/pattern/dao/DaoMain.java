package ru.ifmo.pattern.dao;

import ru.ifmo.pattern.entity.CourseEntity;

import java.sql.SQLException;
import java.util.List;

public class DaoMain {
    public static void main(String[] args) throws SQLException {
        // Допустим, эти параметры берутся из системных переменных.
        final String host = "localhost:5432";
        final String dbName = "ifmo";
        final String username = "ifmo";
        final String password = "q1w2e3";
        final AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory();
        factory.connect(host, dbName, username, password);

        final CourseDao courseDao = factory.createCourseDao();

        final List<CourseEntity> courses = courseDao.getAll();

        System.out.println("== All courses:");
        courses.forEach(System.out::println);
        System.out.println();

        CourseEntity jsCourse = new CourseEntity(null, "JavaScript", 200, 25000.0);

        courseDao.save(jsCourse);

        System.out.println("== New course: ");
        System.out.println(jsCourse);
        System.out.println();

        jsCourse.setPrice(26000.0);
        courseDao.save(jsCourse);

        System.out.println("== Updated course: ");
        System.out.println(jsCourse);
        System.out.println();

        System.out.println("== Got course: ");
        System.out.println(courseDao.get(jsCourse.getId()));
        System.out.println();

        courseDao.remove(jsCourse.getId());

        System.out.println("== Got removed course: ");
        System.out.println(courseDao.get(jsCourse.getId()));
        System.out.println();

        System.out.println("== All courses:");
        courseDao.getAll().forEach(System.out::println);

        factory.close();
    }
}
