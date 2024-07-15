package org.example.view;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Password Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(new UnlockScreen(this));
    }
}