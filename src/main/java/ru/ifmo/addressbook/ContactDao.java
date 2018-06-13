package ru.ifmo.addressbook;

import java.sql.*;
import java.util.List;

public class ContactDao {
    private static final String TABLE_NAME = "CONTACT";

    private final Connection con;



    public ContactDao(Connection con) {
        this.con = con;
    }

    public void save(Contact contact) {
        assert contact != null;

        if (contact.getId() == null) {
            // INSERT
            try (PreparedStatement stmnt =
                         con.prepareStatement("INSERT INTO " + TABLE_NAME + " (id, name, address) " +
                                 "VALUES (?, ?, ?)")) {
                int id = nextId();

                stmnt.setInt(1, id);
                stmnt.setString(2, contact.getName());
                stmnt.setString(3, contact.getAddress());

                stmnt.executeUpdate();

                contact.setId(id);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            // UPDATE
            try (PreparedStatement stmnt =
                         con.prepareStatement("UPDATE " + TABLE_NAME + " SET name=?, address=? " +
                                 "WHERE id=?")) {

                stmnt.setString(1, contact.getName());
                stmnt.setString(2, contact.getAddress());
                stmnt.setInt(3, contact.getId());

                stmnt.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    private int nextId() throws SQLException {
        // SELECT nextval('contact_id_seq');

        try (Statement stmnt = con.createStatement();
             ResultSet rs = stmnt.executeQuery("SELECT nextval('contact_id_seq')")) {
            rs.next();

            return rs.getInt(1);
        }
    }

    public Contact get(int id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void remove(int id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public List<Contact> findByName(String name) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
