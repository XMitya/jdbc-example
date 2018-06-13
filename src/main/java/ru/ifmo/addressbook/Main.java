package ru.ifmo.addressbook;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/ifmo", "ifmo", "q1w2e3")) {
            ContactDao dao = new ContactDao(con);

            Contact vasya = new Contact(null, "Vasyok", "USSR");

            dao.save(vasya);

            JOptionPane.showMessageDialog(null, "Press OK whe ready");

            vasya.setAddress("RF");

            dao.save(vasya);
        }
    }
}
