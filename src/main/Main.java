package main;

import usermanagement.UserManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Update Profile\n4. Delete Profile\n5. View User Profile\n6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                // Register a new user
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter phone: ");
                String phone = scanner.nextLine();
                System.out.print("Enter address: ");
                String address = scanner.nextLine();
                System.out.print("Enter role (customer/admin): ");
                String role = scanner.nextLine();

                UserManager.registerUser(username, password, phone, address);

            } else if (choice == 2) {
                // Login user
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                boolean isLoggedIn = UserManager.loginUser(username, password);
                if (isLoggedIn) {
                    System.out.println("Login successful!");
                }

            } else if (choice == 3) {
                // Update user profile
                System.out.print("Enter username to update: ");
                String username = scanner.nextLine();
                System.out.print("Enter new phone: ");
                String newPhone = scanner.nextLine();
                System.out.print("Enter new address: ");
                String newAddress = scanner.nextLine();
                System.out.print("Enter new role: ");
                String newRole = scanner.nextLine();

                UserManager.updateUserProfile(username, newPhone, newAddress);

            } else if (choice == 4) {
                // Delete user profile
                System.out.print("Enter username to delete: ");
                String username = scanner.nextLine();

                UserManager.deleteUser(username);

            } else if (choice == 5) {
                // View user profile
                System.out.print("Enter username to view profile: ");
                String username = scanner.nextLine();

                UserManager.viewUserProfile(username);

            } else if (choice == 6) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}
