import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactDAO dao = new ContactDAO();

        while (true) {
            System.out.println("\n--- Contact Management System ---");
            System.out.println("1. Add Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.println("6. Search Contact by Name");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    dao.addContact(new Contact(name, phone, email));
                    break;

                case 2:
                    List<Contact> contacts = dao.getAllContacts();
                    if (contacts.isEmpty()) {
                        System.out.println("No contacts found.");
                    } else {
                        for (Contact c : contacts) {
                            System.out.println(c);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Phone: ");
                    String newPhone = sc.nextLine();
                    System.out.print("Enter New Email: ");
                    String newEmail = sc.nextLine();
                    dao.updateContact(new Contact(updateId, newName, newPhone, newEmail));
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    int deleteId = sc.nextInt();
                    dao.deleteContact(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting... Thank you!");
                    return;

                case 6:
                    System.out.print("Enter name to search: ");
                    String searchName = sc.nextLine();
                    List<Contact> results = dao.searchByName(searchName);
                    if (results.isEmpty()) {
                        System.out.println("No contacts found with that name.");
                    } else {
                        for (Contact c : results) {
                            System.out.println(c);
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
                case 7:
                    System.out.print("Enter phone number to search: ");
                    String searchPhone = sc.nextLine();
                    List<Contact> phoneResults = dao.searchByPhone(searchPhone);
                    if (phoneResults.isEmpty()) {
                        System.out.println("No contacts found with that phone number.");
                    } else {
                        for (Contact c : phoneResults) {
                            System.out.println(c);
                        }
                    }
                    break;

            }
        }
    }
}
