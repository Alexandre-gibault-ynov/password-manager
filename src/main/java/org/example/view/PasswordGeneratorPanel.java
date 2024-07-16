package org.example.view;

import org.example.controller.CredentialController;

import javax.swing.*;
import java.awt.*;

/**
 * Password generator panel helps the user to generate a password.
 */
public class PasswordGeneratorPanel extends JPanel {
    public PasswordGeneratorPanel(CredentialController credentialController) {
        setLayout(new GridBagLayout());

        JTextField passwordGeneratorOutputField = new JTextField(100);
        passwordGeneratorOutputField.setEditable(false);
        JLabel passwordGeneratorLabel = new JLabel("Password Generator");

        JSlider passwordGeneratorSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 14);
        passwordGeneratorSlider.setMajorTickSpacing(10);
        passwordGeneratorSlider.setMinorTickSpacing(1);
        JLabel charactersCountLabel = new JLabel(passwordGeneratorSlider.getValue() + " Characters");

        passwordGeneratorSlider.addChangeListener(_ -> {
            int length = passwordGeneratorSlider.getValue();
            charactersCountLabel.setText(length + " Characters");
            passwordGeneratorOutputField.setText(credentialController.generatePassword(length));
        });


        JButton generatePasswordButton = new JButton("Generate");
        generatePasswordButton.addActionListener(_ -> {
            String generatedPassword = credentialController.generatePassword(passwordGeneratorSlider.getValue());
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
}
