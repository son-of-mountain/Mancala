package MancalaUI.Frame;

import MancalaUI.Logic.MancalaPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    public ConfigPanel(MainFrame frame) {
        // Set a background image or solid color for decoration
        setLayout(new BorderLayout());

        // Create a styled title
        JLabel titleLabel = new JLabel("Game Configuration", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 32));
        titleLabel.setForeground(new Color(60, 179, 113)); // Medium sea green
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Create the configuration form with padding and spacing
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 20, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        formPanel.setBackground(new Color(240, 240, 240)); // Light background for form area

        // Add styled labels and dropdowns
        JLabel opponentLabel = createStyledLabel("Opponent:");
        JComboBox<String> opponentDropdown = createStyledDropdown(new String[]{"Player 2 (Human)", "AI"});

        JLabel complexityLabel = createStyledLabel("Game Complexity:");
        JComboBox<String> complexityDropdown = createStyledDropdown(new String[]{"1", "2", "3", "4", "5"});

        JLabel strategyLabel = createStyledLabel("Game Strategy:");
        JComboBox<String> strategyDropdown = createStyledDropdown(new String[]{"Offensive", "Defensive"});

        JLabel heuristicLabel = createStyledLabel("Heuristic:");
        JComboBox<String> heuristicDropdown = createStyledDropdown(new String[]{"Basic", "Advanced"});

        // Add components to the form panel
        formPanel.add(opponentLabel);
        formPanel.add(opponentDropdown);
        formPanel.add(complexityLabel);
        formPanel.add(complexityDropdown);
        formPanel.add(strategyLabel);
        formPanel.add(strategyDropdown);
        formPanel.add(heuristicLabel);
        formPanel.add(heuristicDropdown);

        add(formPanel, BorderLayout.CENTER);

        // Create the start button and style it
        JButton startButton = new JButton("Start the Game");
        styleButton(startButton);
        startButton.addActionListener((ActionEvent e) -> {
            GamePanel gamePanel = frame.getGamePanel();
            gamePanel.configureGame(
                    opponentDropdown.getSelectedIndex(),
                    Integer.parseInt((String) complexityDropdown.getSelectedItem()),
                    (String) strategyDropdown.getSelectedItem(),
                    (String) heuristicDropdown.getSelectedItem()
            );
            frame.switchPanel("Game");
        });

        // Create the load button and style it
        JButton loadButton = new JButton("Load Game");
        styleButton(loadButton);
        loadButton.addActionListener((ActionEvent e) -> {
            String filename = JOptionPane.showInputDialog(this, "Enter filename to load:");
            if (filename != null && !filename.trim().isEmpty()) {
                GamePanel gamePanel = frame.getGamePanel();
                MancalaPosition loadedModel = MancalaPosition.loadGame(filename.trim());
                if (loadedModel != null) {
                    gamePanel.setModel(loadedModel);
                    frame.switchPanel("Game");
                    JOptionPane.showMessageDialog(this, "Game loaded successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to load game.");
                }
            }
        });

        // Create a panel for the buttons with padding
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));
        buttonPanel.add(startButton);
        buttonPanel.add(loadButton); // Add the load button

        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates a styled JLabel with consistent design.
     */
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.RIGHT);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(new Color(46, 139, 87)); // Forest green
        return label;
    }

    /**
     * Creates a styled JComboBox with consistent design.
     */
    private JComboBox<String> createStyledDropdown(String[] options) {
        JComboBox<String> dropdown = new JComboBox<>(options);
        dropdown.setFont(new Font("Arial", Font.PLAIN, 16));
        dropdown.setBackground(Color.WHITE);
        dropdown.setForeground(Color.BLACK);
        dropdown.setBorder(BorderFactory.createLineBorder(new Color(46, 139, 87), 1)); // Border around dropdown
        return dropdown;
    }

    /**
     * Styles a button with consistent design.
     */
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(new Color(60, 179, 113)); // Medium sea green
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(46, 139, 87), 2), // Border color
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // Padding inside the button
        ));
    }
}
