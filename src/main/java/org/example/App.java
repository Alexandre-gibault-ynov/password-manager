package org.example;

import org.example.view.MainFrame;

public class App
{
    public static void main( String[] args )
    {
        javax.swing.SwingUtilities.invokeLater(() ->
            new MainFrame().setVisible(true)
        );
    }
}
