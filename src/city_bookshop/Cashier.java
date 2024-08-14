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
import java.util.List;

public class Cashier extends User {

    public Cashier(String username, String password,String role) {
        super(username, password,role);
        role = "Cashier";
    }

    @Override
    public void viewBooks(List<Book> books) {
        for (Book book : books) {
            System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() +
                               ", Price: $" + book.getPrice() + ", Category: " + book.getCategory() +
                               ", Stock: " + book.getStock());
        }
    }

    @Override
    public Book searchBookByTitle(String title, List<Book> books) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> searchBooksByCategory(String category, List<Book> books) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equals(category)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> searchBooksByPriceRange(double minPrice, double maxPrice, List<Book> books) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getPrice() >= minPrice && book.getPrice() <= maxPrice) {
                result.add(book);
            }
        }
        return result;
    }

    
    public void updateUserProf(String username, String newUsername, String newPassWord) {
        
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(User.FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String role = parts[2];
                if (parts[0].equals(username)) {
                    lines.add(newUsername + "," + newPassWord + "," + role);
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(User.FILE_PATH))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Updated user: " + username + " to " + newUsername );
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    
    
    
}


