package ru.ifmo.pattern.repository.postgres.criteria;

import ru.ifmo.pattern.entity.CourseEntity;
import ru.ifmo.pattern.repository.Mapper;
import ru.ifmo.pattern.repository.SqlCriteria;

public class GetAllCourses implements SqlCriteria<CourseEntity> {
    @Override
    public String toSql(Mapper<CourseEntity> courseMapper) {
        return "select " + courseMapper.columns() + " from " + courseMapper.tableName();
    }
}
