package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultationScreen extends JPanel {
    private MainFrame mainFrame;
    private JList<String> credentialsList;
    private DefaultListModel<String> listModel;
    private JTextField identifierField;
    private JTextField passwordField;
    private JTextField authServiceField;

    public ConsultationScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        credentialsList = new JList<>(listModel);

        identifierField = new JTextField(20);
        passwordField = new JTextField(20);
        authServiceField = new JTextField(20);

        JButton addButton = new JButton("+");
        JButton editButton = new JButton("✎");
        JButton deleteButton = new JButton("🗑");

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!credentialsList.isSelectionEmpty()) {
                    listModel.removeElement(credentialsList.getSelectedIndex());
                }
            }
        });

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JScrollPane(credentialsList), BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JLabel identifierLabel = new JLabel("Identifier:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel authServiceLabel = new JLabel("Auth Service:");

        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        rightPanel.add(identifierLabel, gridBagConstraints);
        gridBagConstraints.gridy++;
        rightPanel.add(passwordLabel, gridBagConstraints);
        gridBagConstraints.gridy++;
        rightPanel.add(authServiceLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        rightPanel.add(identifierField, gridBagConstraints);
        gridBagConstraints.gridy++;
        rightPanel.add(passwordField, gridBagConstraints);
        gridBagConstraints.gridy++;
        rightPanel.add(authServiceField, gridBagConstraints);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
