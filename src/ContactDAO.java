
import java.sql.*;
import java.util.*;

public class ContactDAO {
    private final String url = "jdbc:mysql://localhost:3306/contactdb";
    private final String user = "root";
    private final String password = "root";

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, password);
    }

    public void addContact(Contact contact) {
        String sql = "INSERT INTO contacts(name, phone, email) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getPhone());
            stmt.setString(3, contact.getEmail());
            stmt.executeUpdate();
            System.out.println("Contact added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                contacts.add(new Contact(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public void updateContact(Contact contact) {
        String sql = "UPDATE contacts SET name=?, phone=?, email=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getPhone());
            stmt.setString(3, contact.getEmail());
            stmt.setInt(4, contact.getId());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Contact updated.");
            } else {
                System.out.println("Contact not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(int id) {
        String sql = "DELETE FROM contacts WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Contact deleted.");
            } else {
                System.out.println("Contact not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ NEW METHOD: Search contacts by name
    public List<Contact> searchByName(String name) {
        List<Contact> result = new ArrayList<>();
        String sql = "SELECT * FROM contacts WHERE name LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new Contact(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<Contact> searchByPhone(String phone) {
        List<Contact> result = new ArrayList<>();
        String sql = "SELECT * FROM contacts WHERE phone LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + phone + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new Contact(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
