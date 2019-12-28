package ru.ifmo.pattern.dao.postgres;

import ru.ifmo.pattern.dao.CourseDao;
import ru.ifmo.pattern.entity.CourseEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresCourseDao implements CourseDao {
    private static final String TABLE_NAME = "course";
    private static final String SEQUENCE_NAME = "course_id_seq";
    private static final String COLUMNS = "id, title, duration, price";
    private static final String[] COLS = COLUMNS.split(", ");

    private final Connection connection;

    public PostgresCourseDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(CourseEntity courseEntity) throws SQLException {
        try {
            connection.setAutoCommit(false);
            if (courseEntity.getId() == null) {
                // insert
                try (PreparedStatement pstmt =
                             connection.prepareStatement(
                                     String.format("insert into %s (%s) values(?,?,?,?)", TABLE_NAME, COLUMNS))) {
                    final int id = nextId();

                    pstmt.setInt(1, id);
                    pstmt.setString(2, courseEntity.getTitle());
                    pstmt.setInt(3, courseEntity.getDuration());
                    pstmt.setDouble(4, courseEntity.getPrice());

                    pstmt.executeUpdate();

                    // Устанавливаем присвоенный идентификатор.
                    courseEntity.setId(id);
                }
            }
            else {
                // maybe update
                try (PreparedStatement pstmt = connection.prepareStatement(
                        String.format("insert into %s (%s) values (?, ?, ?, ?) on conflict (id) do " +
                                "update set %s = ?, %s = ?, %s = ?", TABLE_NAME, COLUMNS, COLS[1], COLS[2], COLS[3])
                )) {
                    pstmt.setInt(1, courseEntity.getId());
                    pstmt.setString(2, courseEntity.getTitle());
                    pstmt.setInt(3, courseEntity.getDuration());
                    pstmt.setDouble(4, courseEntity.getPrice());
                    pstmt.setString(5, courseEntity.getTitle());
                    pstmt.setInt(6, courseEntity.getDuration());
                    pstmt.setDouble(7, courseEntity.getPrice());

                    pstmt.executeUpdate();
                }
            }

            connection.commit();
        }
        catch (SQLException e) {
            connection.rollback();

            throw e;
        }
        finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public CourseEntity get(Integer id) throws SQLException {
        try (PreparedStatement pstmt =
                     connection.prepareStatement(String.format("select %s from %s where id = ?", COLUMNS, TABLE_NAME))) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next())
                    return mapCourse(rs);
            }
        }

        return null;
    }

    @Override
    public List<CourseEntity> getAll() throws SQLException {
        List<CourseEntity> res = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            String sql = String.format("select %s from %s", COLUMNS, TABLE_NAME);

            try (ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next())
                    res.add(mapCourse(rs));
            }
        }

        return res;
    }

    private CourseEntity mapCourse(ResultSet rs) throws SQLException {
        return new CourseEntity(
                rs.getInt(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getDouble(4)
        );
    }

    @Override
    public void remove(Integer id) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(String.format("delete from %s where id = ?", TABLE_NAME))) {
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }
    }

    private int nextId() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(String.format("select nextval('%s')", SEQUENCE_NAME))) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }
}
