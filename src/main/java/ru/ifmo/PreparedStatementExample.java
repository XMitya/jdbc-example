package ru.ifmo;

import java.sql.*;

/**
 * Created by xmitya on 17.01.17.
 */
public class PreparedStatementExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Загружаем нужный нам JDBC драйвер
        Class.forName("org.postgresql.Driver");

        // Выполняем подключение, используя загруженный драйвер: ifmo - название БД в PostgreSQL СУБД
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ifmo", "ifmo", "q1w2e3")) {
            // Подготавливаем запрос, который будет закэширован, а аргументы заменяем ?
            PreparedStatement prepared = con.prepareStatement("SELECT * FROM course WHERE duration>=? AND price<=?");
            // Устанавливаем на места ? конкретные аргументы
            prepared.setInt(1, 90);
            prepared.setDouble(2, 20000.0);
            // Выполняем запрос к БД
            try (ResultSet rs = prepared.executeQuery()) {
                // Перемещаем курсор по результам
                while (rs.next()) {
                    // Извлекаем конкретные значения из ResultSet
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    int duration = rs.getInt("duration");
                    double price = rs.getDouble("price");

                    System.out.printf("id: %s, title: %s, duration: %s, price: %s\n", id, title, duration, price);
                }
            }
        }
    }
}
