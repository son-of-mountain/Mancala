package MancalaUI.Frame;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private Image backgroundImage;

    public HomePanel(MainFrame frame) {
        // Load the background image
        backgroundImage = new ImageIcon(getClass().getResource("/images/HomeBackground.png")).getImage();

        // Set layout and transparent background for components
        setLayout(new BorderLayout());

        // Create and style the title
        JLabel titleLabel = new JLabel("Welcome to Mancala!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE); // White text for contrast
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0)); // Padding around the title
        add(titleLabel, BorderLayout.NORTH);

        // Create and style the continue button
        JButton continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.BOLD, 20));
        continueButton.setBackground(new Color(60, 179, 113)); // Medium sea green
        continueButton.setForeground(Color.WHITE);
        continueButton.setFocusPainted(false);
        continueButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding inside the button
        continueButton.addActionListener(e -> frame.switchPanel("Instructions"));

        // Create a panel for the button with padding and alignment
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
