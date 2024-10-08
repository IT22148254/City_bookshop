/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package city_bookshop;

/**
 *
 * @author HP
 */
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddBookDialog extends javax.swing.JDialog {

    /**
     * Creates new form AddBookDialog
     */
    private JTextField titleField;
    private JTextField authorField;
    private JTextField priceField;
    private JTextField categoryField;
    private JTextField stockField;
    private JButton saveButton;
    private Bookshop bookshop;
    private String originalTitle;

    public AddBookDialog(JFrame parent, Bookshop bookshop) {
        super(parent, "Add Book", true);
        this.bookshop = bookshop;

        // Initialize components
        titleField = new JTextField(20);
        authorField = new JTextField(20);
        priceField = new JTextField(20);
        categoryField = new JTextField(20);
        stockField = new JTextField(20);
        saveButton = new JButton("Save");

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
    private void saveBook() {
        try {

            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String priceText = priceField.getText().trim();
            String categoryText = categoryField.getText().trim();
            String stockText = stockField.getText().trim();

            if (title.isEmpty() || author.isEmpty() || priceText.isEmpty() || categoryText.isEmpty() || stockText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Book newBook = new Book(
                    titleField.getText(),
                    authorField.getText(),
                    Double.parseDouble(priceField.getText()),
                    new Category(categoryField.getText()),
                    Integer.parseInt(stockField.getText())
            );

            bookshop.addBook(newBook);
            JOptionPane.showMessageDialog(this, "Book added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error updating book", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
