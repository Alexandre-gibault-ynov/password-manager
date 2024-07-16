package org.example.view;

import org.example.controller.CredentialController;
import org.example.model.Credential;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class ConsultationScreen extends JPanel {
    private final LabeledTextField identifierField;
    private final LabeledTextField passwordField;
    private final LabeledTextField authServiceField;
    private final JList<String> credentialsList;
    private final DefaultListModel<String> listModel;

    public ConsultationScreen(MainFrame mainFrame, CredentialController credentialController) {
        setLayout(new BorderLayout());

        JLabel logo = new JLabel("Password Manager Logo", SwingConstants.CENTER);
        add(logo, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        credentialsList = new JList<>(listModel);

        loadCredentials(credentialController);
        credentialsList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateFields(credentialController);
            }
        });

        JScrollPane credentialsListPane = new JScrollPane(credentialsList);
        credentialsListPane.setMinimumSize(credentialsListPane.getPreferredSize());
        add(credentialsListPane, BorderLayout.WEST);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());

        JPanel credentialsDetailsPanel = new JPanel();
        credentialsDetailsPanel.setLayout(new BoxLayout(credentialsDetailsPanel, BoxLayout.Y_AXIS));

        this.identifierField = new LabeledTextField("Identifier",20, false);
        this.passwordField = new LabeledTextField("Password",20, false);
        this.authServiceField = new LabeledTextField("Auth service",20, false);

        credentialsDetailsPanel.add(Box.createVerticalGlue());
        credentialsDetailsPanel.add(identifierField);
        credentialsDetailsPanel.add(passwordField);
        credentialsDetailsPanel.add(authServiceField);
        credentialsDetailsPanel.add(Box.createVerticalGlue());

        detailsPanel.add(credentialsDetailsPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("+");
        JButton editButton = new JButton("✎");
        JButton deleteButton = new JButton("🗑");
        JButton settingsButton = new JButton("Settings");

        addButton.addActionListener(_ -> mainFrame.switchPanel(new EditScreen(mainFrame, credentialController)));

        editButton.addActionListener(_ -> {
            if (!credentialsList.isSelectionEmpty()) {
                String selectedCredentialName = credentialsList.getSelectedValue();
                credentialController.getCredentials().stream()
                        .filter(cred -> cred.getName().equals(selectedCredentialName))
                        .findFirst()
                        .ifPresent(selectedCredential -> mainFrame.switchPanel(new EditScreen(mainFrame, credentialController, selectedCredential)));
            }
        });

        deleteButton.addActionListener(_ -> {
            if (!credentialsList.isSelectionEmpty()) {
                int reply = JOptionPane.showConfirmDialog(mainFrame, "Are you sure to delete this credential?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
                if (reply == JOptionPane.OK_OPTION) {
                    int selectedCredentialIndex = credentialsList.getSelectedIndex();
                    credentialController.removeCredential(selectedCredentialIndex);
                    listModel.remove(selectedCredentialIndex);
                }
            }
        });

        settingsButton.addActionListener(_ -> {
            JPasswordField newPasswordField = new JPasswordField(20);
            int result = JOptionPane.showConfirmDialog(mainFrame, newPasswordField, "Enter new master password", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String newPassword = new String(newPasswordField.getPassword());
                credentialController.changeMasterPassword(newPassword);
                JOptionPane.showMessageDialog(mainFrame, "Master password changed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(settingsButton);
        detailsPanel.add(buttonPanel, BorderLayout.NORTH);

        add(detailsPanel, BorderLayout.CENTER);
    }

    private void loadCredentials(CredentialController credentialController) {
        listModel.clear();
        for (Credential credential : credentialController.getCredentials()) {
            listModel.addElement(credential.getName());
        }
    }

    private void updateFields(CredentialController credentialController) {
        String selectedCredentialName = credentialsList.getSelectedValue();
        if (selectedCredentialName != null) {
            Optional<Credential> selectedCredential = credentialController.getCredentials().stream()
                    .filter(cred -> cred.getName().equals(selectedCredentialName))
                    .findFirst();
            selectedCredential.ifPresent(credential -> {
                identifierField.setTextFieldText(credential.getIdentifier());
                passwordField.setTextFieldText(credential.getPassword());
                authServiceField.setTextFieldText(credential.getAuthService());
            });
        }
    }
}
