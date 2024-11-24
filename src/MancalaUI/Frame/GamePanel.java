package MancalaUI.Frame;

import MancalaUI.Logic.*;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class GamePanel extends JPanel {
    private MancalaPosition model; // Game model
    private MancalaSearch search; // AI search algorithm
    private JPanel[][] pitPanels; // Panels for pits
    private JPanel[] mancalaPanels; // Panels for mancalas
    private JLabel playerTurnLabel; // Turn indicator
    private boolean isAgainstAI; // True if playing against AI

    public GamePanel(MainFrame frame) {
        setLayout(new BorderLayout());
        model = new MancalaPosition(); // Initialize model
        search = new MancalaSearch(model); // Initialize AI logic

        mancalaPanels = new JPanel[2]; // Two mancala containers

        // Turn indicator at the top
        playerTurnLabel = new JLabel("Player A's Turn", SwingConstants.CENTER);
        playerTurnLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(playerTurnLabel, BorderLayout.NORTH);

        // Main board layout
        JPanel boardPanel = createBoardPanel();
        add(boardPanel, BorderLayout.CENTER);

        // Bottom buttons
        JPanel bottomPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveGame());

        JButton helpButton = new JButton("Help");
        helpButton.addActionListener(e -> requestHelp());

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(e -> loadGame());

        bottomPanel.add(saveButton);
        bottomPanel.add(helpButton);
        bottomPanel.add(loadButton);
        add(bottomPanel, BorderLayout.SOUTH);

        refreshBoard(); // Initial board update
    }

    public void setModel(MancalaPosition loadedModel) {
        this.model = loadedModel; // Update the model with loaded state
        refreshBoard();           // Reflect changes in the UI
    }

    public void configureGame(int opponentType, int complexity, String strategy, String heuristic) {
        model.setComplexity(complexity);
        model.setStrategy(strategy);
        model.setHeuristic(heuristic);
        search.setComplexity(complexity);
        search.setStrategy(strategy);
        isAgainstAI = (opponentType == 1); // True if AI is selected
    }

    private JPanel createBoardPanel() {
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new BorderLayout(10, 10));
        boardPanel.setBackground(new Color(139, 69, 19)); // Light brown

        // Left Mancala (Player B)
        mancalaPanels[0] = createMancalaPanel("B");
        boardPanel.add(mancalaPanels[0], BorderLayout.WEST);

        // Right Mancala (Player A)
        mancalaPanels[1] = createMancalaPanel("A");
        boardPanel.add(mancalaPanels[1], BorderLayout.EAST);

        // Center pits panel
        JPanel pitsPanel = new JPanel(new GridLayout(2, 6, 5, 5));
        pitsPanel.setBackground(new Color(139, 69, 19));
        pitPanels = new JPanel[2][6];

        // Top row: B6 <- B5 <- B4 <- B3 <- B2 <- B1
        for (int i = 0; i < 6; i++) {
            pitPanels[1][i] = createPitPanel("B" + (6 - i), 12 - i); // Adjusted to display in reverse
            pitsPanel.add(pitPanels[1][i]);
        }

        // Bottom row: A1 → A2 → A3 → A4 → A5 → A6
        for (int i = 0; i < 6; i++) {
            pitPanels[0][i] = createPitPanel("A" + (i + 1), i);
            pitsPanel.add(pitPanels[0][i]);
        }

        boardPanel.add(pitsPanel, BorderLayout.CENTER);
        return boardPanel;
    }

    private JPanel createMancalaPanel(String playerLabel) {
        JPanel mancalaPanel = new JPanel(new BorderLayout());
        mancalaPanel.setPreferredSize(new Dimension(100, 150));
        mancalaPanel.setBackground(new Color(139, 69, 19));

        JLabel label = new JLabel(playerLabel, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);

        JLabel mancalaGrains = new JLabel();
        mancalaGrains.setFont(new Font("Arial", Font.PLAIN, 20));
        mancalaGrains.setForeground(Color.BLACK);

        mancalaPanel.add(label, BorderLayout.NORTH);
        mancalaPanel.add(mancalaGrains, BorderLayout.CENTER);
        return mancalaPanel;
    }

    private JPanel createPitPanel(String label, int pitIndex) {
        JPanel pitPanel = new JPanel(new BorderLayout());
        pitPanel.setBackground(new Color(160, 82, 45)); // Dark brown

        JLabel pitLabel = new JLabel(label, SwingConstants.CENTER);
        pitLabel.setFont(new Font("Arial", Font.BOLD, 14));
        pitLabel.setForeground(Color.WHITE);

        JPanel grainsPanel = new JPanel(new GridLayout(2, 3));
        grainsPanel.setOpaque(false);
        updateGrains(grainsPanel, pitIndex);

        pitPanel.add(pitLabel, BorderLayout.NORTH);
        pitPanel.add(grainsPanel, BorderLayout.CENTER);

        pitPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Disable clicking Player B pits if AI is playing
                if (isAgainstAI && model.getCurrentPlayer() == 1) {
                    return; // Ignore clicks on Player B's pits during AI play
                }
                handleMove(pitIndex);
            }
        });

        return pitPanel;
    }

    private void updateGrains(JPanel grainsPanel, int pitIndex) {
        grainsPanel.removeAll();
        int grains = model.getBoard()[pitIndex];
        for (int i = 0; i < grains; i++) {
            JLabel grain = new JLabel("●", SwingConstants.CENTER);
            grain.setForeground(Color.YELLOW);
            grainsPanel.add(grain);
        }
        grainsPanel.revalidate();
        grainsPanel.repaint();
    }

    private void refreshBoard() {
        int[] board = model.getBoard();

        for (int i = 0; i < 6; i++) {
            updateGrains((JPanel) pitPanels[0][i].getComponent(1), i); // Bottom row (Player A)
            updateGrains((JPanel) pitPanels[1][i].getComponent(1), 12 - i); // Top row (Player B)
        }

        ((JLabel) mancalaPanels[0].getComponent(1)).setText(String.valueOf(board[13])); // Mancala B
        ((JLabel) mancalaPanels[1].getComponent(1)).setText(String.valueOf(board[6])); // Mancala A
    }

    private void handleMove(int pitIndex) {
        // Check if move is valid
        if (!model.isValidMove(new MancalaMove(pitIndex))) {
            JOptionPane.showMessageDialog(this, "Invalid move! Try again.");
            return;
        }

        // Make the move for the current player
        model.makeMove(new MancalaMove(pitIndex));
        refreshBoard();

        // Check if the game is over
        if (model.isGameOver()) {
            JOptionPane.showMessageDialog(this, "Game Over! Winner: Player " +
                    (model.getScore(0) > model.getScore(1) ? "A" : "B"));
            return;
        }

        // Switch turn or invoke AI
        if (isAgainstAI && model.getCurrentPlayer() == 1) {
            // AI's turn (Player B)
            performAIMove();
        } else {
            // Update the turn indicator for the next player
            playerTurnLabel.setText("Player " + (model.getCurrentPlayer() == 0 ? "A" : "B") + "'s Turn");
        }
    }


    private void performAIMove() {
        JOptionPane.showMessageDialog(this, "AI is making a move...");

        // Perform AI move for Player B
        Vector<Object> result = search.performAlphaBeta(model, GameSearch.PROGRAM);
        Position bestMove = (Position) result.elementAt(1);

        // Apply the AI's move
        model = (MancalaPosition) bestMove;
        refreshBoard();

        // Check if the game is over after AI's move
        if (model.isGameOver()) {
            JOptionPane.showMessageDialog(this, "Game Over! Winner: Player " +
                    (model.getScore(0) > model.getScore(1) ? "A" : "B"));
        } else {
            // Update the turn indicator for the next player
            playerTurnLabel.setText("Player A's Turn"); // Always back to Player A
        }
    }


    private void saveGame() {
        String filename = JOptionPane.showInputDialog(this, "Enter filename to save:");
        if (filename != null && !filename.trim().isEmpty()) {
            MancalaPosition.saveGame(model, filename.trim());
            JOptionPane.showMessageDialog(this, "Game saved successfully!");
        }
    }

    private void loadGame() {
        String filename = JOptionPane.showInputDialog(this, "Enter filename to load:");
        if (filename != null && !filename.trim().isEmpty()) {
            MancalaPosition loadedModel = MancalaPosition.loadGame(filename.trim());
            if (loadedModel != null) {
                model = loadedModel;
                refreshBoard();
                JOptionPane.showMessageDialog(this, "Game loaded successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load game.");
            }
        }
    }

    private void requestHelp() {
        if (model.getHelpRequestsLeft() > 0) {
            MancalaMove bestMove = model.aiMove();
            JOptionPane.showMessageDialog(this, "Suggested Move: Pit " + bestMove.getPit());
            model.decrementHelpRequestsLeft();
        } else {
            JOptionPane.showMessageDialog(this, "No help requests left!");
        }
    }
}
