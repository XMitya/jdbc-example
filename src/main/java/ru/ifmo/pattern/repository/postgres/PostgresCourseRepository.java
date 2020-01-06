package ru.ifmo.pattern.repository.postgres;

import ru.ifmo.pattern.entity.CourseEntity;
import ru.ifmo.pattern.repository.Mapper;
import ru.ifmo.pattern.repository.Repository;
import ru.ifmo.pattern.repository.SqlCriteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostgresCourseRepository implements Repository<CourseEntity> {
    private final Mapper<CourseEntity> courseMapper;
    private final Connection connection;

    public PostgresCourseRepository(Mapper<CourseEntity> courseMapper, Connection connection) {
        this.courseMapper = courseMapper;
        this.connection = connection;
    }


    @Override
    public void save(CourseEntity course) {
        // Обычная реализация.
    }

    @Override
    public void delete(int id) {
        // Обычная реализация.
    }

    @Override
    public List<CourseEntity> query(SqlCriteria<CourseEntity> criteria) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(criteria.toSql(courseMapper))) {
            criteria.setArguments(ps);

            try (ResultSet rs = ps.executeQuery()) {
                return courseMapper.toEntities(rs);
            }
        }
    }
}
