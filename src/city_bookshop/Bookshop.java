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
import java.util.stream.Collectors;

public class Bookshop {

    private List<Book> books;
    private static final String FILE_PATH_BK = "src/city_bookshop/books.txt";

    public Bookshop() {
        books = new ArrayList<>();
    }

    // Create
    public void addBook(Book book) throws IOException {
        books.add(book);
        saveToFile();
    }

    // Read
    public List<Book> getBooks() throws IOException {
        loadFromFile();
        return books;
    }

    // Update
    public void updateBook(String title, Book updatedBook) throws IOException {
        loadFromFile();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                books.set(i, updatedBook);
                saveToFile();
                return;
            }
        }
        throw new IllegalArgumentException("Book not found");
    }

    // Delete
    public void deleteBook(String title) throws IOException {
        loadFromFile();
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        saveToFile();
    }

    // Save books to file
    private void saveToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_BK))) {
            for (Book book : books) {
                writer.write(book.getTitle() + "," + book.getAuthor() + "," + book.getPrice() + ","
                        + book.getCategory().getName() + "," + book.getStock());
                writer.newLine();
            }
        }
    }

    public List<Book> getBooksByCategory(String categoryName) throws IOException {
        return getBooks().stream()
                .filter(book -> book.getCategory().getName().equalsIgnoreCase(categoryName))
                .collect(Collectors.toList());
    }

    public List<Book> searchBooksByTitle(List<Book> books, String searchText) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(searchText))
                .collect(Collectors.toList());
    }

    public List<Book> filterBooksByPriceRange(List<Book> books, String priceRange) {
        String[] parts = priceRange.split(" - ");
        double lowerBound = Double.parseDouble(parts[0]);
        double upperBound = parts.length > 1 ? Double.parseDouble(parts[1]) : Double.MAX_VALUE;

        return books.stream()
                .filter(book -> book.getPrice() >= lowerBound && book.getPrice() <= upperBound)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {
//        Bookshop bk = new Bookshop();
//        Category c1 = new Category("Suspense");
//        Book book1 = new Book("Harry Potter", "J.K. Rowling", 1200.00, c1, 25);

        // Adding Categories
        Category.addCategory(new Category("Fiction"));
        Category.addCategory(new Category("Non-Fiction"));

        // Reading and displaying all Categories
        List<Category> categories = Category.getAllCategories();
        for (Category category : categories) {
            System.out.println(category.getName());
        }

        // Updating a Category
        Category.updateCategory("Fiction", "Science Fiction");

        // Deleting a Category
        Category.deleteCategory("Non-Fiction");

        // Display all categories after update and delete
        categories = Category.getAllCategories();
        for (Category category : categories) {
            System.out.println(category.getName());
        }

        //bk.addBook(book1);
    }

    // Load books from file
    private void loadFromFile() throws IOException {
        books.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH_BK))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String title = parts[0];
                    String author = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    Category category = new Category(parts[3]); // Assuming categories are simple
                    int stock = Integer.parseInt(parts[4]);
                    books.add(new Book(title, author, price, category, stock));
                }
            }
        }
    }
}
