package city_bookshop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Category {

    private String name;
    static final String FILE_PATH_CG = "src/city_bookshop/category.txt";

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Create or Add a new Category
    public static void addCategory(Category category) {
        if (isCategoryExists(category.getName())) {
            System.out.println("Category already exists: " + category.getName());
            return;
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_CG, true))) {
            writer.write(category.getName());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Check if a Category exists
    public static boolean isCategoryExists(String categoryName) {
        List<Category> categories = getAllCategories();
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return true;
            }
        }
        return false;
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
        if (!isCategoryExists(oldName)) {
            System.out.println("Category not found: " + oldName);
            return;
        }

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
        if (!isCategoryExists(name)) {
            System.out.println("Category not found: " + name);
            return;
        }

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
