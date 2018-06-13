package ru.ifmo;

import java.sql.*;

/**
 * Created by xmitya on 17.01.17.
 */
public class JdbcExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Загружаем нужный нам JDBC драйвер
        Class.forName("org.postgresql.Driver");

        // Выполняем подключение, используя загруженный драйвер: ifmo - название БД в PostgreSQL СУБД
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ifmo", "ifmo", "q1w2e3")) {
            // Создаем statement
            try (Statement stmnt = con.createStatement()) {
                // Выполняем запрос к БД
//                String courseName = "Java'; DROP TABLE grade; SELECT * FROM course WHERE title='";
                try (ResultSet rs = stmnt.executeQuery("SELECT * FROM course WHERE title='Java'")) {
                    // Перемщаем курсор по результам
                    while (rs.next()) {
                        // Извлекаем конкретные значения из ResultSet
                        int id = rs.getInt("id");
                        String title = rs.getString("title");
                        int duration = rs.getInt("duration");
                        double price = rs.getDouble("price");

                        Course course = new Course(id, title, duration, price);

                        System.out.printf("id: %s, title: %s, duration: %s, price: %s\n", id, title, duration, price);
                    }
                }
            }
        }
    }
}
