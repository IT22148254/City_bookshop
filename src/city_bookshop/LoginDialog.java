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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoginDialog extends javax.swing.JDialog {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private boolean succeeded;

    public LoginDialog(Frame parent, boolean par) {
        super(parent, "Login", true);

        // Set up panel and layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(usernameLabel, cs);

        usernameField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(usernameField, cs);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(passwordLabel, cs);

        passwordField = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(passwordField, cs);

        // Role Label and ComboBox
        JLabel roleLabel = new JLabel("Role: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(roleLabel, cs);

        roleComboBox = new JComboBox<>(new String[]{"Cashier", "Manager"});
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(roleComboBox, cs);

        // Set up border for panel
        panel.setBorder(BorderFactory.createTitledBorder("Login Panel"));

        // Login and Cancel buttons
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener((ActionEvent e) -> {
            if (authenticate(getUsername(), getPassword(), getRole())) {
                JOptionPane.showMessageDialog(LoginDialog.this,
                        "Hi " + getUsername() + "! You have successfully logged in.",
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE);
                succeeded = true;
                dispose();

                if ("Manager".equals(getRole())) {

                    mgr_db mgdb = new mgr_db(getUsername(), getPassword(), getRole());
                    mgdb.setVisible(true);
                } else if ("Cashier".equals(getRole())) {

                    cash_db csdb = new cash_db(getUsername(), getPassword(), getRole());
                    csdb.setVisible(true);
                }

                parent.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(LoginDialog.this,
                        "Invalid username or password",
                        "Login",
                        JOptionPane.ERROR_MESSAGE);
                usernameField.setText("");
                passwordField.setText("");
                succeeded = false;
            }
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener((ActionEvent e) -> {
            dispose();
        });

        // Button panel
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);

        // Add components to the dialog
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    // Placeholder authentication method
    private boolean authenticate(String username, String password, String role) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("city_bookshop/users.txt")) {
            if (inputStream == null) {
                throw new FileNotFoundException("Resource not found: users.txt");
            }

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] credentials = line.split(",");
                    if (credentials.length == 3) {
                        String storedUsername = credentials[0].trim();
                        String storedPassword = credentials[1].trim();
                        String storedRole = credentials[2].trim();

                        if (username.equals(storedUsername) && password.equals(storedPassword) && role.equals(storedRole)) {
                            return true; // Authentication successful
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Authentication failed
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getRole() {
        return (String) roleComboBox.getSelectedItem();
    }

    public boolean isSucceeded() {
        return succeeded;
    }
//
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 753, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LoginDialog dialog = new LoginDialog(new JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
