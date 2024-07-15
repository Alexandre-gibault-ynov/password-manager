package org.example;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class PasswordGeneratorPanel extends JPanel {
    public PasswordGeneratorPanel() {
        setLayout(new GridBagLayout());

        JTextField passwordGeneratorOutputField = new JTextField(100);
        passwordGeneratorOutputField.setEditable(false);
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
        add(passwordGeneratorLabel, passwordGeneratorPanelConstraints);

        passwordGeneratorPanelConstraints.gridy++;
        add(passwordGeneratorSlider, passwordGeneratorPanelConstraints);

        passwordGeneratorPanelConstraints.gridx++;
        add(charactersCountLabel, passwordGeneratorPanelConstraints);

        passwordGeneratorPanelConstraints.gridx++;
        add(generatePasswordButton, passwordGeneratorPanelConstraints);

        passwordGeneratorPanelConstraints.gridy++;
        passwordGeneratorPanelConstraints.gridx = 0;
        passwordGeneratorPanelConstraints.gridwidth = 4;
        add(passwordGeneratorOutputField, passwordGeneratorPanelConstraints);
        setMaximumSize(this.getPreferredSize());
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
