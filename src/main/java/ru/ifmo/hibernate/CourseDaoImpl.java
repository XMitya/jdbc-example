package ru.ifmo.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.ifmo.hibernate.entity.CourseEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.function.Function;

public class CourseDaoImpl implements CourseDao {
    private final SessionFactory sessionFactory;

    public CourseDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(CourseEntity courseEntity) {
        runInTransaction(session -> {
            session.saveOrUpdate(courseEntity);
            return null;
        });
    }

    @Override
    public CourseEntity get(Integer id) {
        return runInTransaction(session -> session.get(CourseEntity.class, id));
    }

    @Override
    public List<CourseEntity> getAll() {
        return runInTransaction(session -> session.createQuery("from CourseEntity", CourseEntity.class).list());
    }

    @Override
    public void remove(Integer id) {
        runInTransaction(session -> {
            final CourseEntity course = session.get(CourseEntity.class, id);
            session.remove(course);
            return null;
        });
    }

    @Override
    public List<CourseEntity> getCoursesDurationLessThan(short duration) {
        // HQl/JPQL пример.
        return runInTransaction(session -> {
            final Query<CourseEntity> query = session.createQuery("from CourseEntity where duration < ?1", CourseEntity.class);
            query.setParameter(1, duration);

            return query.list();
        });
    }

    @Override
    public List<CourseEntity> getCoursesPriceLessThan(double price) {
        // Пример с использованием Criteria
        return runInTransaction(session -> {
            final CriteriaBuilder builder = session.getCriteriaBuilder();
            final CriteriaQuery<CourseEntity> criteriaQuery = builder.createQuery(CourseEntity.class);
            final Root<CourseEntity> root = criteriaQuery.from(CourseEntity.class);

            criteriaQuery.select(root).where(builder.lt(root.get("price"), price));

            return session.createQuery(criteriaQuery).list();
        });
    }

    @Override
    public List<CourseEntity> getCoursesWithoutTeacher() {
        return runInTransaction(session ->
                session.createQuery("select c from CourseEntity c left join TeacherEntity t on t.course = c where t is null", CourseEntity.class).list());
    }

    private <T> T runInTransaction(Function<Session, T> function) {
        Transaction tx = null;
        try (final Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            T res = function.apply(session);

            tx.commit();

            return res;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive())
                    tx.rollback();
            } catch (Exception ex) {
                e.addSuppressed(ex);
            }

            throw e;
        }
    }


}
