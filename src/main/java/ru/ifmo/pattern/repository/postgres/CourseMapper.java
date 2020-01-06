package ru.ifmo.pattern.repository.postgres;

import ru.ifmo.pattern.entity.CourseEntity;
import ru.ifmo.pattern.repository.Mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseMapper implements Mapper<CourseEntity> {
    private static final List<String> FIELD_NAMES = List.of("id", "title", "duration", "price");
    private static final String COLUMNS = String.join(", ", FIELD_NAMES);

    @Override
    public List<String> fieldNames() {
        return FIELD_NAMES;
    }

    @Override
    public String columns() {
        return COLUMNS;
    }

    @Override
    public String tableName() {
        return "course";
    }

    @Override
    public List<CourseEntity> toEntities(ResultSet rs) throws SQLException {
        List<CourseEntity> res = new ArrayList<>();

        while (rs.next())
            res.add(toEntity(rs));

        return res;
    }

    @Override
    public CourseEntity toEntity(ResultSet rs) throws SQLException {
        return new CourseEntity(
                rs.getInt(FIELD_NAMES.get(0)),
                rs.getString(FIELD_NAMES.get(1)),
                rs.getInt(FIELD_NAMES.get(2)),
                rs.getDouble(FIELD_NAMES.get(3))
        );
    }

    @Override
    public int id(CourseEntity entity) {
        return entity.getId();
    }

    @Override
    public void setValues(PreparedStatement ps, CourseEntity entity) {
        // todo implement
        throw new UnsupportedOperationException("Not implemented");
    }
}
