package ru.ifmo.pattern.repository.postgres.criteria;

import ru.ifmo.pattern.entity.CourseEntity;
import ru.ifmo.pattern.repository.Mapper;
import ru.ifmo.pattern.repository.SqlCriteria;

import java.util.stream.Collectors;

public class GetCoursesWithoutTeacher implements SqlCriteria<CourseEntity> {
    @Override
    public String toSql(Mapper<CourseEntity> mapper) {
        final String columns = mapper.fieldNames().stream().map(f -> "c." + f).collect(Collectors.joining(", "));

        return "select " + columns + " from " + mapper.tableName() + " c " +
                "left join teacher t on c.id = t.course_id where t.course_id is null"; // Явно задано имя таблицы для простоты.
    }
}
