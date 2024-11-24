package MancalaUI.Frame;


import MancalaUI.Logic.MancalaMove;
import MancalaUI.Logic.MancalaPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardFrame extends JFrame {
    private MancalaPosition position;
    private JLabel[][] pitLabels; // 2D array to represent Player A and Player B pits
    private JLabel mancalaA, mancalaB; // Labels for the Mancalas
    private JLabel statusLabel;

    public BoardFrame(MancalaPosition position) {
        this.position = position;

        // Frame setup
        setTitle("Mancala Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Game board setup
        JPanel boardPanel = createBoardPanel();
        JPanel controlPanel = createControlPanel();

        // Status label
        statusLabel = new JLabel("Game Started! Player 1's Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Add components to the frame
        add(statusLabel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        updateBoard(); // Initialize the board with the current state
    }

    private JPanel createBoardPanel() {
        JPanel boardPanel = new JPanel(new BorderLayout());
        JPanel pitsPanel = new JPanel(new GridLayout(2, 6, 10, 10));
        pitsPanel.setBackground(new Color(240, 224, 200));

        pitLabels = new JLabel[2][6];

        // Create pits for Player B (top row)
        for (int i = 12; i > 6; i--) {
            pitLabels[1][12 - i] = createPitLabel(i);
            pitsPanel.add(pitLabels[1][12 - i]);
        }

        // Create pits for Player A (bottom row)
        for (int i = 0; i < 6; i++) {
            pitLabels[0][i] = createPitLabel(i);
            pitsPanel.add(pitLabels[0][i]);
        }

        // Mancala pits
        mancalaA = createMancalaLabel(6, "A");
        mancalaB = createMancalaLabel(13, "B");

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(mancalaB, BorderLayout.CENTER);
        leftPanel.setBackground(new Color(240, 224, 200));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(mancalaA, BorderLayout.CENTER);
        rightPanel.setBackground(new Color(240, 224, 200));

        boardPanel.add(leftPanel, BorderLayout.WEST);
        boardPanel.add(pitsPanel, BorderLayout.CENTER);
        boardPanel.add(rightPanel, BorderLayout.EAST);

        return boardPanel;
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1, 3, 10, 0));

        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> {
            statusLabel.setText("Undo not implemented yet!"); // Placeholder for undo functionality
        });

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog(this, "Enter filename to save:");
            if (filename != null && !filename.trim().isEmpty()) {
                MancalaPosition.saveGame(position, filename.trim());
                JOptionPane.showMessageDialog(this, "Game saved successfully!");
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); // Close the frame
            }
        });

        controlPanel.add(undoButton);
        controlPanel.add(saveButton);
        controlPanel.add(exitButton);
        return controlPanel;
    }

    private JLabel createPitLabel(int pitIndex) {
        JLabel pitLabel = new JLabel("", SwingConstants.CENTER);
        pitLabel.setOpaque(true);
        pitLabel.setBackground(new Color(220, 200, 180));
        pitLabel.setFont(new Font("Arial", Font.BOLD, 14));
        pitLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pitLabel.setPreferredSize(new Dimension(60, 60));

        pitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handlePitClick(pitIndex);
            }
        });

        return pitLabel;
    }

    private JLabel createMancalaLabel(int index, String player) {
        JLabel mancalaLabel = new JLabel("<html><center>Mancala<br>" + player + "</center></html>", SwingConstants.CENTER);
        mancalaLabel.setOpaque(true);
        mancalaLabel.setBackground(new Color(200, 180, 150));
        mancalaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mancalaLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mancalaLabel.setPreferredSize(new Dimension(100, 150));
        return mancalaLabel;
    }

    private void handlePitClick(int pitIndex) {
        if (!position.isValidMove(new MancalaMove(pitIndex))) {
            statusLabel.setText("Invalid move! Try again.");
            return;
        }

        position.makeMove(new MancalaMove(pitIndex));
        updateBoard();

        if (position.isGameOver()) {
            statusLabel.setText("Game Over! Winner: " + (position.getScore(0) > position.getScore(1) ? "Player 1" : "Player 2"));
        } else if (position.getCurrentPlayer() == 0) {
            statusLabel.setText("Player 1's turn");
        } else {
            statusLabel.setText("Player 2's turn");
        }
    }

    private void updateBoard() {
        int[] board = position.getBoard();
        for (int i = 0; i < 6; i++) {
            pitLabels[0][i].setText(String.valueOf(board[i])); // Player A pits
            pitLabels[1][5 - i].setText(String.valueOf(board[12 - i])); // Player B pits
        }
        mancalaA.setText("<html><center>Mancala<br>" + board[6] + "</center></html>");
        mancalaB.setText("<html><center>Mancala<br>" + board[13] + "</center></html>");
    }
}
