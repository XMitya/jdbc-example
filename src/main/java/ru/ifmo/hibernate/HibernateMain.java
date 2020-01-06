package ru.ifmo.hibernate;

public class HibernateMain {
    public static void main(String[] args) {
        AbstractDaoFactory factory = AbstractDaoFactory.factory();
        factory.connect();
        final CourseDao courseDao = factory.createCourseDao();

        System.out.println(courseDao.getAll());
        System.out.println(courseDao.getCoursesDurationLessThan((short) 100));
        System.out.println(courseDao.getCoursesPriceLessThan(15000));
        System.out.println(courseDao.getCoursesWithoutTeacher());

        factory.close();
    }
}
