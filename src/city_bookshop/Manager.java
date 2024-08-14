/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package city_bookshop;

/**
 *
 * @author HP
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Manager extends Cashier {

    public Manager(String username, String password, String role) {
        super(username, password, role);
        role = "Manager";
    }

    public void addBook(Book book, List<Book> books) {
        books.add(book);
    }

    public void createAccount(String username, String password, String role) {
        String txt = null;
        if (role.equalsIgnoreCase("Cashier")) {

            txt = username + "," + password + "," + "Cashier";
        } else if (role.equalsIgnoreCase("Manager")) {

            txt = username + "," + password + "," + "Manager";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Manager.FILE_PATH, true))) {
            writer.write(txt);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 3) {
                    String username = userData[0];
                    String password = userData[1]; // Consider hashing or encryption in practice
                    String role = userData[2];
                    if (role == "Cashier") {
                        users.add(new Cashier(username, password, role));
                    } else {
                        users.add(new Manager(username, password, role));
                    }

                } else {
                    System.err.println("Malformed line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void addCategory(Category category, List<Category> categories) {
        categories.add(category);
    }

    

    public void deleteAccount(String username) {
        //System.out.println("Deleted account ..... ");

        List<String> lines = new ArrayList<>();
        boolean userFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(Manager.FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[0].equals(username)) {
                    lines.add(line); // Keep line if username doesn't match
                } else {
                    userFound = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (userFound) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(Manager.FILE_PATH))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
                System.out.println("Deleted user: " + username);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("User not found: " + username);
        }

    }
    
}
