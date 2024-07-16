package org.example.view;

import org.example.controller.CredentialController;
import org.example.model.Credential;

import javax.swing.*;
import java.awt.*;

public class EditScreen extends JPanel {
    private final LabeledTextField credentialName;
    private final LabeledTextField identifierField;
    private final LabeledTextField passwordField;
    private final LabeledTextField authServiceField;

    public EditScreen(MainFrame mainFrame, CredentialController credentialController ) {
        this(mainFrame, credentialController, null);
    }

    public EditScreen(MainFrame mainFrame, CredentialController credentialController, Credential currentCredential) {

        setLayout(new BorderLayout());

        // Credentials list at left
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> credentialsList = new JList<>(listModel);

        for (Credential credential : credentialController.getCredentials()) {
            listModel.addElement(credential.getName());
        }
        JScrollPane scrollPane = new JScrollPane(credentialsList);
        add(scrollPane, BorderLayout.WEST);


        JPanel mainPanel = new JPanel(new GridBagLayout());

        credentialName = new LabeledTextField("Credential Name", 20);
        identifierField = new LabeledTextField("Identifier",20);
        passwordField = new LabeledTextField("Password",20);
        authServiceField = new LabeledTextField("Auth service",20);

        GridBagConstraints mainPanelConstraints = new GridBagConstraints();
        mainPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanelConstraints.weightx = 1;
        mainPanelConstraints.weighty = 1;
        mainPanelConstraints.gridx = 0;
        mainPanelConstraints.gridy = 0;

        mainPanel.add(credentialName, mainPanelConstraints);
        mainPanelConstraints.gridy++;
        mainPanel.add(identifierField, mainPanelConstraints);
        mainPanelConstraints.gridy++;
        mainPanel.add(passwordField, mainPanelConstraints);
        mainPanelConstraints.gridy++;
        mainPanel.add(authServiceField, mainPanelConstraints);

        mainPanelConstraints.gridy++;
        mainPanelConstraints.gridx = 0;
        mainPanelConstraints.gridwidth = 2;
        mainPanel.add(new PasswordGeneratorPanel(credentialController), mainPanelConstraints);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(_ -> {
            int reply = JOptionPane.showConfirmDialog(mainFrame, "Are you sure to go back ?", "Back", JOptionPane.OK_CANCEL_OPTION);
            if (reply == JOptionPane.OK_OPTION) {
                mainFrame.switchPanel(new ConsultationScreen(mainFrame, credentialController));
            }

        });
        buttonPanel.add(backButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(_ -> {
            String name = credentialName.getTextFieldText();
            String identifier = identifierField.getTextFieldText();
            String password = passwordField.getTextFieldText();
            String authService = authServiceField.getTextFieldText();

            if (currentCredential == null) {
                credentialController.saveCredential(name, identifier, password, authService
                );
            } else {
                int selectedCredentialIndex = credentialsList.getSelectedIndex();
                credentialController.updateCredential(selectedCredentialIndex, name, identifier, password, authService);
            }
            JOptionPane.showMessageDialog(mainFrame,"Credential successfully saved");
            mainFrame.switchPanel(new ConsultationScreen(mainFrame, credentialController));
        });
        buttonPanel.add(saveButton);

        mainPanelConstraints.gridy++;
        mainPanelConstraints.gridwidth = 2;
        mainPanel.add(buttonPanel, mainPanelConstraints);

        add(mainPanel, BorderLayout.CENTER);

        // If editing an existing credential, populate the fields
        if (currentCredential != null) {
            credentialName.setTextFieldText(currentCredential.getName());
            identifierField.setTextFieldText(currentCredential.getIdentifier());
            passwordField.setTextFieldText(currentCredential.getPassword());
            authServiceField.setTextFieldText(currentCredential.getAuthService());
        }
    }
}
