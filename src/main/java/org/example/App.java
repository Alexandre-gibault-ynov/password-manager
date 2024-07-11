package org.example;

import javax.swing.*;

public class App
{
    public static void main( String[] args )
    {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
