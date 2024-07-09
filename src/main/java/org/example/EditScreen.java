package org.example;

import javax.swing.*;
import java.awt.*;

public class EditScreen extends JPanel {
    private final MainFrame mainFrame;
    private JTextField identifierField;
    private JTextField passwordField;
    private JTextField authServiceField;
    private JTextField passwordGeneratorOutputField;

    public EditScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        identifierField = new JTextField(20);
        passwordField = new JTextField(20);
        authServiceField = new JTextField(20);
        passwordGeneratorOutputField = new JTextField(20);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JLabel identifierLabel = new JLabel("Identifier:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel authServiceLabel = new JLabel("Authentication Service:");
        JLabel passwordGeneratorLabel = new JLabel("Password Generator:");

        JSlider passwordGeneratorSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 14);
        passwordGeneratorSlider.setMajorTickSpacing(10);
        passwordGeneratorSlider.setMinorTickSpacing(1);
        passwordGeneratorSlider.setPaintLabels(true);

        passwordGeneratorSlider.addChangeListener(e -> {
            int length = passwordGeneratorSlider.getValue();
            passwordGeneratorOutputField.setText(generatePassword(length));
        });

        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        rightPanel.add(identifierLabel, gridBagConstraints);
        gridBagConstraints.gridy++;
        rightPanel.add(passwordLabel, gridBagConstraints);
        gridBagConstraints.gridy++;
        rightPanel.add(authServiceLabel, gridBagConstraints);
        gridBagConstraints.gridy++;
        rightPanel.add(passwordGeneratorLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        rightPanel.add(identifierField, gridBagConstraints);
        gridBagConstraints.gridy++;
        rightPanel.add(passwordField, gridBagConstraints);
        gridBagConstraints.gridy++;
        rightPanel.add(authServiceField, gridBagConstraints);
        gridBagConstraints.gridy++;
        rightPanel.add(passwordGeneratorSlider, gridBagConstraints);
        gridBagConstraints.gridy++;

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            mainFrame.setContentPane(new ConsultationScreen(mainFrame));
            mainFrame.validate();
        });

        buttonPanel.add(saveButton);

        add(rightPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private String generatePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt((int) (Math.random() * chars.length())));
        }
        return password.toString();
    }
}