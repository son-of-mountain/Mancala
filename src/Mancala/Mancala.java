package Mancala;

import java.util.Scanner;

public class Mancala {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MancalaPosition position = new MancalaPosition();
        boolean gameRunning = true;

        while (gameRunning) {
            System.out.println("\n====================================");
            System.out.println("          Mancala Game Menu         ");
            System.out.println("====================================");
            System.out.println("1. Start a New Game");
            System.out.println("2. Load a Saved Game");
            System.out.println("3. Replay a Saved Game");
            System.out.println("4. Quit");
            System.out.println("====================================");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> startNewGame(position, scanner);
                case 2 -> loadSavedGame(scanner);
                case 3 -> replaySavedGame(scanner);
                case 4 -> {
                    System.out.println("Exiting the game. Goodbye!");
                    gameRunning = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void startNewGame(MancalaPosition position, Scanner scanner) {
        System.out.println("\n====================================");
        System.out.println("        Start a New Game            ");
        System.out.println("====================================");
        System.out.println("Do you want to play against:");
        System.out.println("1. Player 2");
        System.out.println("2. AI");
        System.out.print("Enter your choice: ");
        int opponentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean playWithAI = (opponentChoice == 2);

        System.out.print("Enter game complexity (1-5): ");
        int complexity = scanner.nextInt();
        scanner.nextLine();
        position.setComplexity(complexity);

        System.out.print("Enter game strategy (offensive/defensive): ");
        String strategy = scanner.nextLine();
        position.setStrategy(strategy);

        System.out.print("Enter heuristic (basic/advanced): ");
        String heuristic = scanner.nextLine();
        position.setHeuristic(heuristic);

        playGame(position, scanner, playWithAI);
    }

    private static void loadSavedGame(Scanner scanner) {
        System.out.println("\n====================================");
        System.out.println("          Load Saved Game           ");
        System.out.println("====================================");
        System.out.print("Enter filename to load: ");
        String filename = scanner.nextLine();
        MancalaPosition loadedPosition = MancalaPosition.loadGame(filename);
        if (loadedPosition != null) {
            System.out.println("Game loaded successfully!");
            playGame(loadedPosition, scanner, loadedPosition.getCurrentPlayer() == 1);
        } else {
            System.out.println("Failed to load game. Returning to menu.");
        }
    }

    private static void replaySavedGame(Scanner scanner) {
        System.out.println("\n====================================");
        System.out.println("         Replay Saved Game          ");
        System.out.println("====================================");
        System.out.print("Enter filename to replay: ");
        String filename = scanner.nextLine();
        MancalaPosition loadedPosition = MancalaPosition.loadGame(filename);
        if (loadedPosition != null) {
            System.out.println("Replaying the saved game...");
            replayGame(loadedPosition);
        } else {
            System.out.println("Failed to load game. Returning to menu.");
        }
    }

    private static void replayGame(MancalaPosition position) {
        while (!position.isGameOver()) {
            printBoard(position);
            MancalaMove move = position.aiMove();
            position.makeMove(move);
        }
        printBoard(position);
        System.out.println("\n====================================");
        System.out.println("          Replay Complete           ");
        System.out.println("====================================");
    }

    private static void playGame(MancalaPosition position, Scanner scanner, boolean playWithAI) {
        while (!position.isGameOver()) {
            printBoard(position);
            System.out.println("\n====================================");
            System.out.println("Player " + (position.getCurrentPlayer() + 1) + "'s turn");
            System.out.println("====================================");
            System.out.println("Help requests remaining: " + position.getHelpRequestsLeft());
            System.out.println("Options:");
            System.out.println(" - Enter a pit number to play (0-5 for Player 1, 7-12 for Player 2)");
            System.out.println(" - Type 'H' for help");
            System.out.println(" - Type 'save' to save the game and return to menu");
            System.out.println(" - Type 'exit' to exit the game without saving");
            System.out.println("====================================");
            System.out.print("Your choice: ");

            // If it's the player's turn (human)
            if (position.getCurrentPlayer() == 0 || !playWithAI) {
                String input = scanner.nextLine();

                if ("H".equalsIgnoreCase(input)) { // Help request
                    if (position.getHelpRequestsLeft() > 0) {
                        position.decrementHelpRequestsLeft();
                        MancalaMove suggestedMove = position.aiMove();
                        System.out.println("\n====================================");
                        System.out.println("AI suggests: Play pit " + suggestedMove.getPit());
                        System.out.println("Remaining help requests: " + position.getHelpRequestsLeft());
                        System.out.println("====================================");
                    } else {
                        System.out.println("No help requests left!");
                    }
                    continue; // Continue the game loop after help
                } else if ("save".equalsIgnoreCase(input)) { // Save the game
                    System.out.print("Enter filename to save the game: ");
                    String filename = scanner.nextLine();
                    MancalaPosition.saveGame(position, filename);
                    System.out.println("\n====================================");
                    System.out.println("Game saved successfully! Returning to menu...");
                    System.out.println("====================================");
                    return; // Exit to the main menu
                } else if ("exit".equalsIgnoreCase(input)) { // Exit without saving
                    System.out.println("\n====================================");
                    System.out.println("Exiting the game without saving. Returning to menu...");
                    System.out.println("====================================");
                    return; // Exit to the main menu
                }

                // Handle the player's move
                try {
                    int pit = Integer.parseInt(input);
                    MancalaMove move = new MancalaMove(pit);
                    if (!position.isValidMove(move)) {
                        System.out.println("Invalid move. Try again.");
                    } else {
                        position.makeMove(move);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid pit number, 'H', 'save', or 'exit'.");
                }
            } else { // If it's the AI's turn
                System.out.println("AI is making a move...");
                MancalaMove aiMove = position.aiMove(); // Get the AI's move
                position.makeMove(aiMove); // Make the move
            }
        }

        // End of the game
        printBoard(position);
        System.out.println("\n====================================");
        System.out.println("              Game Over             ");
        System.out.println("====================================");
        System.out.println("Player 1 Score: " + position.getScore(0));
        System.out.println("Player 2 Score: " + position.getScore(1));
    }


    private static void printBoard(MancalaPosition position) {
        int[] board = position.getBoard();
        System.out.println("\n====================================");
        System.out.println("            Current Board           ");
        System.out.println("====================================");
        System.out.println("P2 | " + board[12] + " " + board[11] + " " + board[10] + " " + board[9] + " " + board[8] + " " + board[7] + " | Mancala: " + board[13]);
        System.out.println("P1 | " + board[0] + " " + board[1] + " " + board[2] + " " + board[3] + " " + board[4] + " " + board[5] + " | Mancala: " + board[6]);
    }
}
