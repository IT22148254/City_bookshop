/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package city_bookshop;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class EditBookDialog extends javax.swing.JDialog {

    /**
     * Creates new form EditBookDialog
     */
    private JTextField titleField;
    private JTextField authorField;
    private JTextField priceField;
    private JTextField categoryField;
    private JTextField stockField;
    private JButton saveButton;
    private Bookshop bookshop;
    private String originalTitle;

    public EditBookDialog(JFrame parent, Bookshop bookshop, String originalTitle) throws IOException {
        super(parent, "Edit Book", true);
        this.bookshop = bookshop;
        this.originalTitle = originalTitle;

        // Initialize components
        titleField = new JTextField(20);
        authorField = new JTextField(20);
        priceField = new JTextField(20);
        categoryField = new JTextField(20);
        stockField = new JTextField(20);
        saveButton = new JButton("Save");

        // Load existing book data
        loadBookData(originalTitle);

        saveButton.addActionListener(e -> saveBook());

        // Layout components using GroupLayout
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel titleLabel = new JLabel("Title:");
        JLabel authorLabel = new JLabel("Author:");
        JLabel priceLabel = new JLabel("Price:");
        JLabel categoryLabel = new JLabel("Category:");
        JLabel stockLabel = new JLabel("Stock:");

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(titleLabel)
                                        .addComponent(authorLabel)
                                        .addComponent(priceLabel)
                                        .addComponent(categoryLabel)
                                        .addComponent(stockLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(titleField)
                                        .addComponent(authorField)
                                        .addComponent(priceField)
                                        .addComponent(categoryField)
                                        .addComponent(stockField)
                                        .addComponent(saveButton))
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(titleLabel)
                                .addComponent(titleField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(authorLabel)
                                .addComponent(authorField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(priceLabel)
                                .addComponent(priceField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(categoryLabel)
                                .addComponent(categoryField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(stockLabel)
                                .addComponent(stockField))
                        .addComponent(saveButton)
        );

        add(panel);
        pack();
        setLocationRelativeTo(parent);
    }

    private void loadBookData(String title) throws IOException {
        try {
            Book book = bookshop.getBooksByTitle(title);
            titleField.setText(book.getTitle());
            authorField.setText(book.getAuthor());
            priceField.setText(String.valueOf(book.getPrice()));
            categoryField.setText(book.getCategory().getName());
            stockField.setText(String.valueOf(book.getStock()));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error loading book data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveBook() {
        try {
            Book updatedBook = new Book(
                    titleField.getText(),
                    authorField.getText(),
                    Double.parseDouble(priceField.getText()),
                    new Category(categoryField.getText()),
                    Integer.parseInt(stockField.getText())
            );
            bookshop.updateBook(originalTitle, updatedBook);
            JOptionPane.showMessageDialog(this, "Book updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error updating book", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create a JFrame to act as the parent window
        JFrame frame = new JFrame("Test Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Dummy Bookshop object with some test data
        Bookshop testBookshop = new Bookshop();
        // Add a test book to the bookshop
        try {
            testBookshop.addBook(new Book("Test Book", "Test Author", 19.99, new Category("Fiction"), 10));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Show the dialog with the test book's title
        SwingUtilities.invokeLater(() -> {
            EditBookDialog dialog = null;
            try {
                dialog = new EditBookDialog(frame, testBookshop, "Test Book");
            } catch (IOException ex) {
                Logger.getLogger(EditBookDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            dialog.setVisible(true);
        });

        frame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
