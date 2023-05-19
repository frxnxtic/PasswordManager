package com.frxnxtic.passwordmanager.ui;

import com.frxnxtic.passwordmanager.generator.PasswordGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainFrame extends JFrame{
    public MainFrame() {
        setTitle("Password Manager by frxnxtic");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(4, 12, 10, 10));

        JLabel startLabel = new JLabel("Welcome to Password Manager");
        JLabel passwordLabel = new JLabel();
        JCheckBox alphacapsCheckBox = new JCheckBox("Include Uppercase");
        JCheckBox alphaCheckBox = new JCheckBox("Include Lowercase");
        JCheckBox numericCheckBox = new JCheckBox("Include numbers");
        JCheckBox specialcharsCheckBox = new JCheckBox("Include special characters");
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.add(alphacapsCheckBox);
        checkBoxPanel.add(alphaCheckBox);
        checkBoxPanel.add(numericCheckBox);
        checkBoxPanel.add(specialcharsCheckBox);
        JButton generateButton = new JButton("Generate password");

        generateButton.setFocusPainted(false);
        alphacapsCheckBox.setFocusPainted(false);
        alphaCheckBox.setFocusPainted(false);
        numericCheckBox.setFocusPainted(false);
        specialcharsCheckBox.setFocusPainted(false);

        startLabel.setHorizontalAlignment(SwingConstants.CENTER);
        generateButton.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        checkBoxPanel.setAlignmentX(SwingConstants.CENTER);

        panel.add(startLabel);
        panel.add(passwordLabel);
        panel.add(checkBoxPanel);
        panel.add(generateButton);

        AtomicBoolean includeUppercase = new AtomicBoolean(false);
        AtomicBoolean includeLowercase = new AtomicBoolean(false);
        AtomicBoolean includeNumbers = new AtomicBoolean(false);
        AtomicBoolean includeSpecialCharacters = new AtomicBoolean(false);


        ActionListener checkBoxListener = e -> {
            includeUppercase.set(alphacapsCheckBox.isSelected());
            includeLowercase.set(alphaCheckBox.isSelected());
            includeNumbers.set(numericCheckBox.isSelected());
            includeSpecialCharacters.set(specialcharsCheckBox.isSelected());
        };

        alphacapsCheckBox.addActionListener(checkBoxListener);
        alphaCheckBox.addActionListener(checkBoxListener);
        numericCheckBox.addActionListener(checkBoxListener);
        specialcharsCheckBox.addActionListener(checkBoxListener);

        generateButton.addActionListener(e -> {
            try {
                String password = PasswordGenerator.generatePassword(12, includeUppercase.get(), includeLowercase.get(),
                        includeNumbers.get(), includeSpecialCharacters.get());
                passwordLabel.setText(password);
            } catch (IllegalArgumentException exception){
                passwordLabel.setText("At least one character set must be selected.");
            }
        });

        getContentPane().add(panel, BorderLayout.CENTER);
    }
}
