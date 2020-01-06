package ru.ifmo.hibernate;

import ru.ifmo.hibernate.entity.CourseEntity;

import java.util.List;

public interface CourseDao extends Dao<CourseEntity> {

    List<CourseEntity> getCoursesDurationLessThan(short duration);
    List<CourseEntity> getCoursesPriceLessThan(double price);
    List<CourseEntity> getCoursesWithoutTeacher();
}
