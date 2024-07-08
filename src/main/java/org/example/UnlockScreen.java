package org.example;

import javax.swing.*;
import java.awt.*;

public class UnlockScreen extends JPanel {
    private MainFrame mainFrame;

    public UnlockScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JLabel logoPanel = new JLabel("Password Manager Logo");
        JPasswordField passwordField = new JPasswordField(20);
        JButton unlockButton = new JButton("Unlock");

        unlockButton.addActionListener(e -> {
            if (passwordField.getPassword().equals("admin")) {

            } else {
                JOptionPane.showMessageDialog(mainFrame, "Invalid password", "Error", JOptionPane.ERROR_MESSAGE);
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
