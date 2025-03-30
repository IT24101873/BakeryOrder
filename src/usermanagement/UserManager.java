package usermanagement;

import FileHandler.FileHandler;
import java.util.List;
import java.util.ArrayList;

public class UserManager {
    private String username;
    private String password;
    private String phone;
    private String address;

    // Default constructor
    public UserManager() {}

    // Parameterized constructor
    public UserManager(String username, String password, String phone, String address) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    // Getters (No password getter for security)
    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // Setters (No username setter to prevent changes)
    public void setPassword(String password) {
        this.password = password; // You can add encryption here
    }

    public void setPhone(String phone) {
        if (phone.matches("\\d{10}")) { // Simple validation
            this.phone = phone;
        } else {
            System.out.println("Invalid phone number.");
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Register a new user
    public static void registerUser(String username, String password, String phone, String address) {
        List<String> users = FileHandler.readFromFile("users.txt");

        // Check if username already exists
        for (String user : users) {
            String[] parts = user.split(",");
            if (parts[0].equals(username)) {
                System.out.println("Username already exists. Try a different one.");
                return;
            }
        }

        // Add new user
        users.add(username + "," + password + "," + phone + "," + address);
        FileHandler.writeToFile("users.txt", users);
        System.out.println("User registered successfully!");
    }

    // Login a user
    public static boolean loginUser(String username, String password) {
        List<String> users = FileHandler.readFromFile("users.txt");

        for (String user : users) {
            String[] parts = user.split(",");
            if (parts[0].equals(username) && parts[1].equals(password)) {
                System.out.println("Login successful! Welcome " + username);
                return true;
            }
        }

        System.out.println("Invalid username or password.");
        return false;
    }

    // Update user details
    public static void updateUserProfile(String username, String newPhone, String newAddress) {
        List<String> users = FileHandler.readFromFile("users.txt");
        boolean updated = false;

        for (int i = 0; i < users.size(); i++) {
            String[] parts = users.get(i).split(",");
            if (parts[0].equals(username)) {
                parts[2] = newPhone; // Update phone
                parts[3] = newAddress; // Update address

                users.set(i, String.join(",", parts));
                updated = true;
                break;
            }
        }

        if (updated) {
            FileHandler.writeToFile("users.txt", users);
            System.out.println("Profile updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    // View user profile
    public static void viewUserProfile(String username) {
        List<String> users = FileHandler.readFromFile("users.txt");

        for (String user : users) {
            String[] parts = user.split(",");
            if (parts[0].equals(username)) {
                System.out.println("Username: " + parts[0]);
                System.out.println("Phone: " + parts[2]);
                System.out.println("Address: " + parts[3]);
                return;
            }
        }

        System.out.println("User not found.");
    }

    // Delete user
    public static void deleteUser(String username) {
        List<String> users = FileHandler.readFromFile("users.txt");
        boolean removed = users.removeIf(user -> user.split(",")[0].equals(username));

        if (removed) {
            FileHandler.writeToFile("users.txt", users);
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    // View all customers
    public static void viewAllCustomers() {
        List<String> users = FileHandler.readFromFile("users.txt");

        if (users.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            System.out.println("All Customers:");
            for (String user : users) {
                String[] parts = user.split(",");
                System.out.println("Username: " + parts[0] + ", Phone: " + parts[2] + ", Address: " + parts[3]);
            }
        }
    }
}
