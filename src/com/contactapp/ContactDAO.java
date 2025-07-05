package com.contactapp;

import java.sql.*;

public class ContactDAO {

    public void addContact(Contact c) {
        String sql = "INSERT INTO contacts(name, phone, email) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getPhone());
            stmt.setString(3, c.getEmail());
            stmt.executeUpdate();
            System.out.println("Contact added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewContacts() {
        String sql = "SELECT * FROM contacts";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ": " + rs.getString("name") + " | " +
                        rs.getString("phone") + " | " + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(int id) {
        String sql = "DELETE FROM contacts WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0)
                System.out.println("Contact deleted.");
            else
                System.out.println("Contact not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateContact(Contact c) {
        String sql = "UPDATE contacts SET name=?, phone=?, email=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getPhone());
            stmt.setString(3, c.getEmail());
            stmt.setInt(4, c.getId());
            int rows = stmt.executeUpdate();
            if (rows > 0)
                System.out.println("Contact updated.");
            else
                System.out.println("Contact not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
