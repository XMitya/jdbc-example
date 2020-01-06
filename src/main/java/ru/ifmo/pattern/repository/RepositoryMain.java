package ru.ifmo.pattern.repository;

import ru.ifmo.pattern.entity.CourseEntity;
import ru.ifmo.pattern.repository.postgres.criteria.GetAllCourses;
import ru.ifmo.pattern.repository.postgres.criteria.GetCoursesDurationLessThan;
import ru.ifmo.pattern.repository.postgres.criteria.GetCoursesPriceLessThan;
import ru.ifmo.pattern.repository.postgres.criteria.GetCoursesWithoutTeacher;

import java.sql.SQLException;

public class RepositoryMain {
    public static void main(String[] args) throws SQLException {
        AbstractRepositoryFactory factory = AbstractRepositoryFactory.getFactory();

        factory.connect();

        final Repository<CourseEntity> repository = factory.createRepository(CourseEntity.class);

        System.out.println("All courses: " + repository.query(new GetAllCourses()));
        System.out.println("Courses with duration less than 100: " + repository.query(new GetCoursesDurationLessThan(100)));
        System.out.println("Courses with price less than 15000: " + repository.query(new GetCoursesPriceLessThan(15000)));
        System.out.println("Courses without teacher: " + repository.query(new GetCoursesWithoutTeacher()));

        factory.close();
    }
}
