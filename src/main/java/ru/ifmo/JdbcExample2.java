package ru.ifmo;

import java.sql.*;

/**
 * Created by xmitya on 17.01.17.
 */
public class JdbcExample2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Загружаем нужный нам JDBC драйвер
        Class.forName("org.postgresql.Driver");

        // Выполняем подключение, используя загруженный драйвер: ifmo - название БД в PostgreSQL СУБД
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ifmo", "ifmo", "q1w2e3")) {
            // Создаем statement
            try (Statement stmnt = con.createStatement()) {
                // Выполняем запрос к БД

                int rows = stmnt.executeUpdate("INSERT INTO course(title, duration, price) VALUES ('JavaScript', 102, 34000)");
                System.out.println(rows);
            }
        }
    }
}
