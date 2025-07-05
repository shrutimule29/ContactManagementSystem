import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ContactAppGUI extends JFrame {
    private JTextField nameField, phoneField, emailField;
    private JTextField searchField, deleteField, updateIdField;
    private JTextArea outputArea;
    private ContactDAO dao = new ContactDAO();

    public ContactAppGUI() {
        setTitle("Contact Management System");
        setSize(700, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel (Form)
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        JButton addButton = new JButton("Add Contact");
        formPanel.add(addButton);

        JButton viewButton = new JButton("View All");
        formPanel.add(viewButton);

        add(formPanel, BorderLayout.NORTH);

        // Center Panel (Output)
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(outputArea);
        add(scroll, BorderLayout.CENTER);

        // Bottom Panel (Search, Delete, Update)
        JPanel bottomPanel = new JPanel(new FlowLayout());

        bottomPanel.add(new JLabel("Search:"));
        searchField = new JTextField(10);
        bottomPanel.add(searchField);
        JButton searchButton = new JButton("Search");
        bottomPanel.add(searchButton);

        bottomPanel.add(new JLabel("Delete ID:"));
        deleteField = new JTextField(5);
        bottomPanel.add(deleteField);
        JButton deleteButton = new JButton("Delete");
        bottomPanel.add(deleteButton);

        bottomPanel.add(new JLabel("Update ID:"));
        updateIdField = new JTextField(5);
        bottomPanel.add(updateIdField);
        JButton updateButton = new JButton("Update");
        bottomPanel.add(updateButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Add Contact
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                outputArea.setText("Please fill all fields.");
                return;
            }
            dao.addContact(new Contact(name, phone, email));
            outputArea.setText("Contact added:\n" + name + " - " + phone + " - " + email);
            clearFields();
        });

        // View All Contacts
        viewButton.addActionListener(e -> {
            List<Contact> contacts = dao.getAllContacts();
            outputArea.setText("");
            if (contacts.isEmpty()) {
                outputArea.setText("No contacts found.");
            } else {
                for (Contact c : contacts) {
                    outputArea.append(c.toString() + "\n");
                }
            }
        });

        // Search Contact
        searchButton.addActionListener(e -> {
            String keyword = searchField.getText().trim();
            List<Contact> nameResults = dao.searchByName(keyword);
            List<Contact> phoneResults = dao.searchByPhone(keyword);

            outputArea.setText("");
            if (nameResults.isEmpty() && phoneResults.isEmpty()) {
                outputArea.setText("No contacts found.");
            } else {
                outputArea.append("Results by Name:\n");
                for (Contact c : nameResults) {
                    outputArea.append(c.toString() + "\n");
                }
                outputArea.append("\nResults by Phone:\n");
                for (Contact c : phoneResults) {
                    outputArea.append(c.toString() + "\n");
                }
            }
        });

        // Delete Contact
        deleteButton.addActionListener(e -> {
            String idText = deleteField.getText().trim();
            if (!idText.matches("\\d+")) {
                outputArea.setText("Please enter a valid numeric ID.");
                return;
            }

            int id = Integer.parseInt(idText);
            dao.deleteContact(id);
            outputArea.setText("Tried to delete contact with ID: " + id);
            deleteField.setText("");
        });

        // Update Contact
        updateButton.addActionListener(e -> {
            String idText = updateIdField.getText().trim();
            if (!idText.matches("\\d+")) {
                outputArea.setText("Please enter a valid numeric ID.");
                return;
            }

            int id = Integer.parseInt(idText);
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                outputArea.setText("Please fill all fields to update the contact.");
                return;
            }

            Contact updatedContact = new Contact(id, name, phone, email);
            dao.updateContact(updatedContact);
            outputArea.setText("Contact updated:\n" + updatedContact);
            updateIdField.setText("");
            clearFields();
        });

        setVisible(true);
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {
        new ContactAppGUI();
    }
}
