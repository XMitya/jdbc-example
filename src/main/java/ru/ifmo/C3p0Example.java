package ru.ifmo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xmitya on 17.01.17.
 */
public class C3p0Example {
    public static void main(String[] args) throws SQLException {
        try (Connection con = DataSource.getConnection()) {
            try (PreparedStatement prepared = con.prepareStatement("SELECT * FROM course WHERE title=?")) {

                prepared.setString(1, "Java");

                try (ResultSet rs = prepared.executeQuery()) {
                    while (rs.next()) {
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
}
