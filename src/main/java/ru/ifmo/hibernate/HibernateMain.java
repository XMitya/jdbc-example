package ru.ifmo.hibernate;

import ru.ifmo.hibernate.entity.CourseEntity;

public class HibernateMain {
    public static void main(String[] args) {
        AbstractDaoFactory factory = AbstractDaoFactory.factory();
        factory.connect();
        final CourseDao courseDao = factory.createCourseDao();

        final CourseEntity brainfuck = new CourseEntity(null, "Brainfuck", (short) 1024, 67000.0);
        courseDao.save(brainfuck);
        System.out.println(brainfuck);

        brainfuck.setPrice(68000.0);
        courseDao.save(brainfuck);

        System.out.println(courseDao.getAll());
        System.out.println(courseDao.getCoursesDurationLessThan((short) 100));
        System.out.println(courseDao.getCoursesPriceLessThan(15000));
        System.out.println(courseDao.getCoursesWithoutTeacher());

        courseDao.remove(brainfuck.getId());

        factory.close();
    }
}
