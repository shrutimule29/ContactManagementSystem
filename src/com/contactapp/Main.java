package com.contactapp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactDAO dao = new ContactDAO();

        while (true) {
            System.out.println("\n--- Contact Management System ---");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Delete Contact");
            System.out.println("4. Update Contact");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    dao.addContact(new Contact(name, phone, email));
                    break;
                case 2:
                    dao.viewContacts();
                    break;
                case 3:
                    System.out.print("Enter contact ID to delete: ");
                    int id = sc.nextInt();
                    dao.deleteContact(id);
                    break;
                case 4:
                    System.out.print("Enter contact ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new phone: ");
                    String newPhone = sc.nextLine();
                    System.out.print("Enter new email: ");
                    String newEmail = sc.nextLine();
                    Contact updated = new Contact(newName, newPhone, newEmail);
                    updated.setId(uid);
                    dao.updateContact(updated);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
