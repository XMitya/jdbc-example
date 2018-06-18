package ru.ifmo;

import java.sql.*;
import java.util.function.Consumer;

/**
 * Created by xmitya on 17.01.17.
 */
public class JdbcExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Загружаем нужный нам JDBC драйвер
        Class.forName("org.postgresql.Driver");

        insert(new Course(0, "HO-HO-HO", 500, 100));
    }

    private static void insert(Course c) throws SQLException {
        inTransaction(con -> {
            try {
                PreparedStatement stmnt = con.prepareStatement("INSERT INTO course (title, duration, price)" +
                        " values (?, ? ,?)");

                stmnt.setString(1, c.getTitle());
                stmnt.setInt(2, c.getDuration());
                stmnt.setDouble(3, c.getPrice());

                stmnt.executeUpdate();

                if (true)
                    throw new RuntimeException("Test exception");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void inTransaction(Consumer<Connection> op) throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ifmo", "ifmo", "q1w2e3")) {
            con.setAutoCommit(false);

            try {
                con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                op.accept(con);

                con.commit();
            } catch (Exception e) {
                e.printStackTrace();

                con.rollback();
            }


        }
    }
}
