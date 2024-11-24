package MancalaUI.Frame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private GamePanel gamePanel;

    public MainFrame() {
        setTitle("Mancala Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        // Initialiser les panneaux
        HomePanel homePanel = new HomePanel(this);
        InstructionPanel instructionPanel = new InstructionPanel(this);
        ConfigPanel configPanel = new ConfigPanel(this);
        gamePanel = new GamePanel(this);

        // Ajouter les panneaux
        add(homePanel, "Home");
        add(instructionPanel, "Instructions");
        add(configPanel, "Config");
        add(gamePanel, "Game");

        // Afficher le premier panneau
        cardLayout.show(getContentPane(), "Home");
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void switchPanel(String panelName) {
        cardLayout.show(getContentPane(), panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
