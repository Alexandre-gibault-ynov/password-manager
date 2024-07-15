package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnlockScreen extends JPanel {
    private final MainFrame mainFrame;

    public UnlockScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JLabel logoPanel = new JLabel("Password Manager Logo");
        JPasswordField passwordField = new JPasswordField(20);
        JButton unlockButton = new JButton("Unlock");

        unlockButton.addActionListener(e -> {
            // Validate master password (for demonstration, we assume it's "admin")
            if (new String(passwordField.getPassword()).equals("admin")) {
                mainFrame.setContentPane(new ConsultationScreen(mainFrame));
                mainFrame.validate();
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Invalid Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(logoPanel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        add(passwordField, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        add(unlockButton, gridBagConstraints);
    }
}
