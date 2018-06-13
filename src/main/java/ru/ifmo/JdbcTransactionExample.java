package ru.ifmo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by xmitya on 17.01.17.
 */
public class JdbcTransactionExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Загружаем нужный нам JDBC драйвер
        Class.forName("org.postgresql.Driver");

        // Выполняем подключение, используя загруженный драйвер: ifmo - название БД в PostgreSQL СУБД
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ifmo", "ifmo", "q1w2e3")) {
            // Выключяем автокоммит транзакций
            con.setAutoCommit(false);

            // Создаем statement
            try (Statement stmnt = con.createStatement()) {
                // Выполняем запрос к БД

                int rows = stmnt.executeUpdate("INSERT INTO course(title, duration, price) VALUES ('Brainfuck', 300, 99000)");
                System.out.println(rows);

                // Если все хорошо завершаем тразакцию
                con.commit();
            }
            catch (SQLException e) {
                // В случае ошибки откатываем изменения
                con.rollback();
            }
        }
    }
}
