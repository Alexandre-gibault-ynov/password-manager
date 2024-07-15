package org.example.view;

import javax.swing.*;
import java.awt.*;

public class ConsultationScreen extends JPanel {
    private final JList<String> credentialsList;
    private final DefaultListModel<String> listModel;

    public ConsultationScreen(MainFrame mainFrame) {
        setLayout(new BorderLayout());

        JLabel logo = new JLabel("Password Manager Logo", SwingConstants.CENTER);
        add(logo, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        credentialsList = new JList<>(listModel);

        JScrollPane credentialsListPane = new JScrollPane(credentialsList);
        add(credentialsListPane, BorderLayout.WEST);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());

        JPanel credentialsDetailsPanel = new JPanel();
        credentialsDetailsPanel.setLayout(new BoxLayout(credentialsDetailsPanel, BoxLayout.Y_AXIS));

        LabeledTextField identifierField = new LabeledTextField("Identifier",20, false);
        LabeledTextField passwordField = new LabeledTextField("Password",20, false);
        LabeledTextField authServiceField = new LabeledTextField("Auth service",20, false);

        credentialsDetailsPanel.add(Box.createVerticalGlue());
        credentialsDetailsPanel.add(identifierField);
        credentialsDetailsPanel.add(passwordField);
        credentialsDetailsPanel.add(authServiceField);
        credentialsDetailsPanel.add(Box.createVerticalGlue());

        detailsPanel.add(credentialsDetailsPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("+");
        JButton editButton = new JButton("✎");
        JButton deleteButton = new JButton("🗑");

        addButton.addActionListener(e -> {
            mainFrame.setContentPane(new EditScreen(mainFrame));
            mainFrame.validate();
        });

        editButton.addActionListener(e -> {
            // Load selected credential for editing
            // For simplicity, we assume only one item for now
            if (!credentialsList.isSelectionEmpty()) {
                mainFrame.setContentPane(new EditScreen(mainFrame));
                mainFrame.validate();
            }
        });

        deleteButton.addActionListener(e -> {
            if (!credentialsList.isSelectionEmpty()) {
                listModel.removeElement(credentialsList.getSelectedIndex());
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        detailsPanel.add(buttonPanel, BorderLayout.NORTH);

        add(detailsPanel, BorderLayout.CENTER);
    }
}