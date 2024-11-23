package Mancala;

import java.io.*;
import java.util.Arrays;

public class MancalaPosition extends Position implements Serializable {
    private static final long serialVersionUID = 1L;
    private int[] board;
    private int currentPlayer;
    private int helpRequestsLeft;
    private int complexity;
    private String strategy;
    private String heuristic;

    public MancalaPosition() {
        board = new int[14];
        Arrays.fill(board, 4);
        board[6] = 0;
        board[13] = 0;
        currentPlayer = 0;
        helpRequestsLeft = 3; // Exemple : Autoriser 3 demandes d'aide par partie
        complexity = 3; // Exemple : Complexité par défaut
        strategy = "default"; // Exemple : Stratégie par défaut
        heuristic = "basic"; // Exemple : Heuristique par défaut
    }

    public int[] getBoard() {
        return board;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = 1 - currentPlayer;
    }

    public boolean isGameOver() {
        return (Arrays.stream(board, 0, 6).sum() == 0 || Arrays.stream(board, 7, 13).sum() == 0);
    }

    public void makeMove(Move move) {
        MancalaMove mancalaMove = (MancalaMove) move;
        int pit = mancalaMove.getPit();
        int stones = board[pit];
        board[pit] = 0;
        int index = pit;

        while (stones > 0) {
            index = (index + 1) % 14;
            if (currentPlayer == 0 && index == 13) continue;
            if (currentPlayer == 1 && index == 6) continue;
            board[index]++;
            stones--;
        }

        if (currentPlayer == 0 && index < 6 && board[index] == 1 && board[12 - index] > 0) {
            board[6] += board[12 - index] + 1;
            board[index] = 0;
            board[12 - index] = 0;
        } else if (currentPlayer == 1 && index > 6 && index < 13 && board[index] == 1 && board[12 - index] > 0) {
            board[13] += board[12 - index] + 1;
            board[index] = 0;
            board[12 - index] = 0;
        }

        if ((currentPlayer == 0 && index == 6) || (currentPlayer == 1 && index == 13)) {
            return;
        }

        switchPlayer();
    }

    public int getScore(int player) {
        return board[player == 0 ? 6 : 13];
    }

    public static void saveGame(MancalaPosition position, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(position);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MancalaPosition loadGame(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (MancalaPosition) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MancalaMove aiMove() {
        // Implémentation simplifiée de l'IA
        for (int i = 0; i < 6; i++) {
            if (currentPlayer == 0 && board[i] > 0) return new MancalaMove(i);
            if (currentPlayer == 1 && board[i + 7] > 0) return new MancalaMove(i + 7);
        }
        return null;
    }

    public int getHelpRequestsLeft() {
        return helpRequestsLeft;
    }

    public void decrementHelpRequestsLeft() {
        if (helpRequestsLeft > 0) helpRequestsLeft--;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public void setHeuristic(String heuristic) {
        this.heuristic = heuristic;
    }
}
