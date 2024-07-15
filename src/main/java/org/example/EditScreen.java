package org.example;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class EditScreen extends JPanel {
    private LabeledTextField credentialName;
    private LabeledTextField identifierField;
    private LabeledTextField passwordField;
    private LabeledTextField authServiceField;
    private JTextField passwordGeneratorOutputField;
    private JList<String> credentialsList;
    private DefaultListModel<String> listModel;

    public EditScreen(MainFrame mainFrame) {
        setLayout(new BorderLayout());

        // Credentials list at left
        listModel = new DefaultListModel<>();
        credentialsList = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(credentialsList);
        add(scrollPane, BorderLayout.WEST);


        JPanel mainPanel = new JPanel(new GridBagLayout());

        credentialName = new LabeledTextField("Credential Name", 20);
        identifierField = new LabeledTextField("Identifier",20);
        passwordField = new LabeledTextField("Password",20);
        authServiceField = new LabeledTextField("Auth service",20);
        passwordGeneratorOutputField = new JTextField(100);
        passwordGeneratorOutputField.setEditable(false);


        JPanel passwordGeneratorPanel = new JPanel(new GridBagLayout());

        JLabel passwordGeneratorLabel = new JLabel("Password Generator");

        JSlider passwordGeneratorSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 14);
        passwordGeneratorSlider.setMajorTickSpacing(10);
        passwordGeneratorSlider.setMinorTickSpacing(1);
        JLabel charactersCountLabel = new JLabel(passwordGeneratorSlider.getValue() + " Characters");

        passwordGeneratorSlider.addChangeListener(e -> {
            int length = passwordGeneratorSlider.getValue();
            charactersCountLabel.setText(passwordGeneratorSlider.getValue() + " Characters");
            passwordGeneratorOutputField.setText(generatePassword(length));
        });


        JButton generatePasswordButton = new JButton("Generate");
        generatePasswordButton.addActionListener(e -> {
            String generatedPassword = generatePassword(passwordGeneratorSlider.getValue());
            passwordGeneratorOutputField.setText(generatedPassword);
        });

        GridBagConstraints passwordGeneratorPanelConstraints = new GridBagConstraints();
        passwordGeneratorPanelConstraints.insets = new Insets(5, 5, 5, 5);
        passwordGeneratorPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        passwordGeneratorPanelConstraints.gridx = 0;
        passwordGeneratorPanelConstraints.gridy = 0;
        passwordGeneratorPanel.add(passwordGeneratorLabel, passwordGeneratorPanelConstraints);

        passwordGeneratorPanelConstraints.gridy++;
        passwordGeneratorPanel.add(passwordGeneratorSlider, passwordGeneratorPanelConstraints);

        passwordGeneratorPanelConstraints.gridx++;
        passwordGeneratorPanel.add(charactersCountLabel, passwordGeneratorPanelConstraints);

        passwordGeneratorPanelConstraints.gridx++;
        passwordGeneratorPanel.add(generatePasswordButton, passwordGeneratorPanelConstraints);

        passwordGeneratorPanelConstraints.gridy++;
        passwordGeneratorPanelConstraints.gridx = 0;
        passwordGeneratorPanelConstraints.gridwidth = 4;
        passwordGeneratorPanel.add(passwordGeneratorOutputField, passwordGeneratorPanelConstraints);

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
        mainPanel.add(passwordGeneratorPanel, mainPanelConstraints);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            int reply = JOptionPane.showConfirmDialog(mainFrame, "Are you sure to go back ?", "Back", JOptionPane.OK_CANCEL_OPTION);
            if (reply == JOptionPane.OK_OPTION) {
                mainFrame.setContentPane(new ConsultationScreen(mainFrame));
                mainFrame.validate();
            }

        });
        buttonPanel.add(backButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainFrame,"Credential successfully saved");
            mainFrame.setContentPane(new ConsultationScreen(mainFrame));
            mainFrame.validate();
        });
        buttonPanel.add(saveButton);

        mainPanelConstraints.gridy++;
        mainPanelConstraints.gridwidth = 2;
        mainPanel.add(buttonPanel, mainPanelConstraints);

        add(mainPanel, BorderLayout.CENTER);
    }

    private String generatePassword(int length) {
        PasswordGenerator generator = new PasswordGenerator();
        CharacterRule lowerCaseRule = new CharacterRule(EnglishCharacterData.LowerCase, 1);
        CharacterRule upperCaseRule = new CharacterRule(EnglishCharacterData.UpperCase, 1);
        CharacterRule digitRule = new CharacterRule(EnglishCharacterData.Digit, 1);
        CharacterRule specialCharRule = new CharacterRule(EnglishCharacterData.Special, 1);

        return length >= 4 ? generator.generatePassword(length, Arrays.asList(lowerCaseRule, upperCaseRule, digitRule, specialCharRule)) : generator.generatePassword(length);
    }
}
