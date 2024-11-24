package MancalaUI.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InstructionPanel extends JPanel {
    private Image backgroundImage;

    public InstructionPanel(MainFrame frame) {
        // Load the background image
        backgroundImage = new ImageIcon(getClass().getResource("/images/InstructionPageBackground.png")).getImage();

        // Set layout for the panel
        setLayout(new BorderLayout());




        // Create and style the continue button
        JButton continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.BOLD, 20));
        continueButton.setBackground(new Color(60, 179, 113)); // Medium sea green
        continueButton.setForeground(Color.WHITE);
        continueButton.setFocusPainted(false);
        continueButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(46, 139, 87), 2), // Border color
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // Padding inside the button
        ));
        continueButton.addActionListener((ActionEvent e) -> frame.switchPanel("Config"));

        // Create a panel for the button with padding
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make the button panel transparent
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50)); // Add margin around the button panel
        buttonPanel.add(continueButton);

        // Add the button panel to the bottom of the layout
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image, scaling it to fill the panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
