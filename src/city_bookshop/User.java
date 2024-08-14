/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package city_bookshop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public abstract class User {

    protected String username;
    protected String password;
    protected String role;
    static final String FILE_PATH = "src/city_bookshop/users.txt";

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = (role == null || role.isEmpty()) ? "" : role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract void viewBooks(List<Book> books);

    public abstract Book searchBookByTitle(String title, List<Book> books);

    public void updateUser(String username, String newUsername, String newRole) {

        //System.out.println("Updated account ..... ");
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Manager.FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String passWd = parts[1];
                if (parts[0].equals(username)) {
                    lines.add(newUsername + "," + passWd + "," + newRole);
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Manager.FILE_PATH))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Updated user: " + username + " to " + newUsername + " with role " + newRole);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
