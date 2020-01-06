package ru.ifmo.pattern.repository.postgres.criteria;

import ru.ifmo.pattern.entity.CourseEntity;
import ru.ifmo.pattern.repository.Mapper;
import ru.ifmo.pattern.repository.SqlCriteria;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetCoursesDurationLessThan implements SqlCriteria<CourseEntity> {
    private final int duration;

    public GetCoursesDurationLessThan(int duration) {
        this.duration = duration;
    }

    @Override
    public String toSql(Mapper<CourseEntity> courseMapper) {
        return "select " + courseMapper.columns() + " from " + courseMapper.tableName() + " where duration < ?";
    }

    @Override
    public void setArguments(PreparedStatement ps) throws SQLException {
        ps.setInt(1, duration);
    }
}
