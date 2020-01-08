package ru.ifmo.hibernate;


import ru.ifmo.hibernate.entity.CourseEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class JpaEntityManagerMain {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence");
        final EntityManager em = factory.createEntityManager();

        printAllCourses(em);
        printCoursesWithoutTeacher(em);
        final CourseEntity newCourse = addAndUpdateCourse(em);
        printCourseByTitle(em, newCourse.getTitle());
        deleteCourse(em, newCourse);
        printCourseByTitle(em, newCourse.getTitle());

        em.close();
        factory.close();
    }

    @SuppressWarnings("unchecked")
    private static void printCourseByTitle(EntityManager em, String title) {
        System.out.println("== Course by title: " + title);

        final List<CourseEntity> courses = em.createQuery("from CourseEntity where title = ?1")
                .setParameter(1, title)
                .getResultList();

        System.out.println(courses);
        System.out.println();
    }

    private static void deleteCourse(EntityManager em, CourseEntity courseEntity) {
        System.out.println("== Delete course: " + courseEntity);
        em.getTransaction().begin();

        em.remove(courseEntity);

        em.getTransaction().commit();

        System.out.println();
    }

    private static CourseEntity addAndUpdateCourse(EntityManager em) {
        System.out.println("== Add Pascal course");
        final CourseEntity pascal = new CourseEntity(null, "Pascal", (short) 100, 10000.0);

        em.getTransaction().begin();

        // Здесь мы привязываем к сессии сущность.
        em.persist(pascal);
        // Что позволяет выполнить обновление значения сущности без явного
        // сохранения после этого.
        pascal.setDuration((short) 110);

        em.getTransaction().commit();
        System.out.println();

        return pascal;
    }

    @SuppressWarnings("unchecked")
    private static void printCoursesWithoutTeacher(EntityManager em) {
        System.out.println("== Courses without teacher");
        final List<CourseEntity> coursesWithoutTeacher =
                em.createQuery("select c from CourseEntity c left join TeacherEntity t on t.course = c where t is null")
                        .getResultList();

        System.out.println(coursesWithoutTeacher);
        System.out.println();
    }

    @SuppressWarnings("unchecked")
    private static void printAllCourses(EntityManager em) {
        System.out.println("== All courses");
        em.getTransaction().begin();
        final Query allCoursesQuery = em.createQuery("select c from CourseEntity c");
        final List<CourseEntity> allCourses = allCoursesQuery.getResultList();

        em.getTransaction().commit();

        System.out.println(allCourses);
        System.out.println();
    }
}
