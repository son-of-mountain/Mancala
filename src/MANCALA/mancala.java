package MANCALA;

import java.util.Scanner;

public class mancala {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MancalaPosition position = new MancalaPosition();
        boolean gameRunning = true;

        while (gameRunning) {
            System.out.println("\n--- Mancala Game Menu ---");
            System.out.println("1. Start a New Game");
            System.out.println("2. Load a Saved Game");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    startNewGame(position, scanner);
                    break;
                case 2:
                    loadSavedGame(position, scanner);
                    break;
                case 3:
                    System.out.println("Exiting the game. Goodbye!");
                    gameRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void startNewGame(MancalaPosition position, Scanner scanner) {
        System.out.println("Do you want to play against:");
        System.out.println("1. Player 2");
        System.out.println("2. AI");
        System.out.print("Enter your choice: ");
        int opponentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean playWithAI = (opponentChoice == 2);

        System.out.print("Enter game complexity (1-5): ");
        int complexity = scanner.nextInt();
        position.setComplexity(complexity);

        System.out.print("Enter game strategy (offensive/defensive): ");
        String strategy = scanner.next();
        position.setStrategy(strategy);

        System.out.print("Enter heuristic (basic/advanced): ");
        String heuristic = scanner.next();
        position.setHeuristic(heuristic);

        playGame(position, scanner, playWithAI);
    }

    private static void loadSavedGame(MancalaPosition position, Scanner scanner) {
        System.out.print("Enter filename to load: ");
        String filename = scanner.nextLine();
        MancalaPosition loadedPosition = MancalaPosition.loadGame(filename);
        if (loadedPosition != null) {
            System.out.println("Game loaded successfully!");
            System.out.println("Do you want to play against:");
            System.out.println("1. Player 2");
            System.out.println("2. AI");
            System.out.print("Enter your choice: ");
            int opponentChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            boolean playWithAI = (opponentChoice == 2);

            playGame(loadedPosition, scanner, playWithAI);
        } else {
            System.out.println("Failed to load game. Returning to menu.");
        }
    }

    private static void playGame(MancalaPosition position, Scanner scanner, boolean playWithAI) {
        while (!position.isGameOver()) {

            

            printBoard(position);
            System.out.println("Player " + (position.getCurrentPlayer() + 1) + "'s turn.");

            // Human player or Player 2 turn
            if (position.getCurrentPlayer() == 0 || !playWithAI) {

                System.out.print("Choose a pit (0-5 for Player 1, 7-12 for Player 2) or type 'help' for assistance: ");
                String input = scanner.next();



                System.out.println("Mini-Menu");
                System.out.println("Help Write H");
                System.out.println("Save Write S");
                System.out.println("Quit Write Q");

                if ("H".equalsIgnoreCase(input)) {
                    if (position.getHelpRequestsLeft() > 0) {
                        position.decrementHelpRequestsLeft();
                        System.out.println("Help: AI suggests pit: " + position.aiMove().getPit());
                    } else {
                        System.out.println("No help requests left!");
                    }
                    continue;
                }

                if ("S".equalsIgnoreCase(input)) {
                    System.out.print("Enter filename to save: ");
                    String filename = scanner.next();
                    MancalaPosition.saveGame(position, filename);
                    System.out.println("Game saved! Returning to menu.");
                    return; // Exit to menu after saving
                }

                if ("Q".equalsIgnoreCase(input)) {
                    System.out.println("Exiting the game. Goodbye!");
                    return;
                }

                int pit = Integer.parseInt(input);
                if (pit >= 0 && pit <= 12 && position.getBoard()[pit] > 0 &&
                        ((position.getCurrentPlayer() == 0 && pit <= 5) || (position.getCurrentPlayer() == 1 && pit >= 7))) {
                    MancalaMove move = new MancalaMove(pit);
                    position.makeMove(move);
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }
            // AI turn
            else {
                System.out.println("AI is making a move...");
                MancalaMove aiMove = position.aiMove();
                position.makeMove(aiMove);
            }
        }

        // Game over
        System.out.println("Game Over!");
        printBoard(position);
        System.out.println("Player 1 Score: " + position.getScore(0));
        System.out.println("Player 2 Score: " + position.getScore(1));
        return;
    }

    private static void printBoard(MancalaPosition position) {
        int[] board = position.getBoard();
        System.out.println("\nCurrent Board:");
        System.out.println("P2 | " + board[12] + " " + board[11] + " " + board[10] + " " + board[9] + " " + board[8] + " " + board[7] + " | Mancala: " + board[13]);
        System.out.println("P1 | " + board[0] + " " + board[1] + " " + board[2] + " " + board[3] + " " + board[4] + " " + board[5] + " | Mancala: " + board[6]);
    }
}
