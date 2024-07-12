package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents a labeled text field. It is composed of a label above a text field.
 */
public class LabeledTextField extends JPanel {

    private final JTextField textField;

    /**
     * Constructor that put a label with the provided text above
     * the text field.
     *
     * @param labelText The label of the text field
     * @param textFieldLength The length of the text field
     */
    public LabeledTextField(String labelText, int textFieldLength) {
        setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints;
        gridBagConstraints = new GridBagConstraints();

        JLabel label = new JLabel(labelText);
        textField = new JTextField(textFieldLength);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(label, gridBagConstraints);
        gridBagConstraints.gridy++;
        add(textField, gridBagConstraints);
    }

    /**
     * Get the text contained by the labeled text field
     *
     * @return The text of the labeled text field
     */
    public String getTextFieldText() {
        return textField.getText();
    }

    /**
     * Set the text of the labeled text field
     *
     * @param text the text to set to the labeled text field
     */
    public void setTextFieldText(String text) {
        this.textField.setText(text);
    }

    /**
     * Enable or disable the possibility to edit the labeled text field
     *
     * @param editable The boolean that set the possibility to edit the labeled text field
     */
    public void setTextFieldEditable(boolean editable) {
        textField.setEditable(editable);
    }
}
