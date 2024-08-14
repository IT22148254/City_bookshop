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

public class Category {

    private String name;
//    private List<Book> books;
    static final String FILE_PATH_CG = "src/city_bookshop/category.txt";

    public Category(String name) {
        this.name = name;
//        this.books = new ArrayList<>();
    }

//    public void addBook(Book book) {
//        books.add(book);
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Book> getBooks() {
//        return books;
//    }
    // Create or Add a new Category
    public static void addCategory(Category category) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_CG, true))) {
            writer.write(category.getName());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read all Categories
    public static List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH_CG))) {
            String line;
            while ((line = reader.readLine()) != null) {
                categories.add(new Category(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categories;
    }

    // Update a Category (rename it)
    public static void updateCategory(String oldName, String newName) {
        List<Category> categories = getAllCategories();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_CG))) {
            for (Category category : categories) {
                if (category.getName().equalsIgnoreCase(oldName)) {
                    category.setName(newName);
                }
                writer.write(category.getName());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Delete a Category
    public static void deleteCategory(String name) {
        List<Category> categories = getAllCategories();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_CG))) {
            for (Category category : categories) {
                if (!category.getName().equalsIgnoreCase(name)) {
                    writer.write(category.getName());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
