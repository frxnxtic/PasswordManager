package com.frxnxtic.passwordmanager.ui;

import com.frxnxtic.passwordmanager.generator.PasswordGenerator;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    public MainFrame() {
        setTitle("Password Manager by frxnxtic");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(3, 12, 10, 10)); // 2 строки, 1 столбец, отступы 10 пикселей

        JLabel startLabel = new JLabel("Welcome to Password Manager");
        JLabel passwordLabel= new JLabel();
        JButton generateButton = new JButton("Generate password");

        generateButton.setFocusPainted(false);

        startLabel.setHorizontalAlignment(SwingConstants.CENTER);
        generateButton.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(startLabel);
        panel.add(passwordLabel);
        panel.add(generateButton);

        generateButton.addActionListener(e -> {
            String password = PasswordGenerator.generatePassword(12, true, true, true, true);
            passwordLabel.setText(password);
        });

        getContentPane().add(panel, BorderLayout.CENTER);
    }
}
