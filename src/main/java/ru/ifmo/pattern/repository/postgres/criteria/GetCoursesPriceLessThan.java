package ru.ifmo.pattern.repository.postgres.criteria;

import ru.ifmo.pattern.entity.CourseEntity;
import ru.ifmo.pattern.repository.Mapper;
import ru.ifmo.pattern.repository.SqlCriteria;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetCoursesPriceLessThan implements SqlCriteria<CourseEntity> {
    private final double price;

    public GetCoursesPriceLessThan(double price) {
        this.price = price;
    }

    @Override
    public String toSql(Mapper<CourseEntity> courseMapper) {
        return "select " + courseMapper.columns() + " from " + courseMapper.tableName() + " where price < ?";
    }

    @Override
    public void setArguments(PreparedStatement ps) throws SQLException {
        ps.setDouble(1, price);
    }
}
