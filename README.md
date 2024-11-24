# Mancala Game

This repository contains a Java-based implementation of the classic **Mancala** board game. The project is built using **Java Swing** for its graphical user interface (GUI), making it interactive and visually appealing. The game supports two modes:
- **Player vs. Player**
- **Player vs. AI**
![image](https://github.com/user-attachments/assets/b1b61dc5-cd08-4f3d-b15d-ff630387749f)

## Features

- **Beautiful GUI:** A modern and responsive design using Java Swing components.
- **AI Opponent:** Play against the computer with different strategies and complexities.
- **Save & Load Game:** Save your progress and resume later.
- **Customizable Configuration:** Adjust the game's complexity, strategy, and heuristic.

### AI Strategy: Alpha-Beta Pruning

This project uses the **Alpha-Beta pruning algorithm** as the core AI strategy for Player B when the "AI" option is selected. Alpha-Beta pruning optimizes the Minimax algorithm by reducing the number of game states the AI evaluates, ensuring efficient and intelligent decision-making. The AI evaluates moves using a heuristic function that considers the number of stones in each player's Mancala and the pits' states. This allows the AI to simulate several moves ahead, choosing the optimal move while adhering to the configured complexity and strategy (offensive or defensive). The AI will always take its turn as Player B immediately after Player A's move.

## Table of Contents

1. [Screenshots](#screenshots)
2. [Installation](#installation)
3. [Usage](#usage)
4. [Game Rules](#game-rules)
5. [Contributing](#contributing)
6. [License](#license)

## Screenshots

### Home Screen
![image](https://github.com/user-attachments/assets/63a72ba6-940b-4ab6-9f5f-6b0306e0655d)

_Description: This is the entry point of the game, where users are greeted with a welcome screen and options to proceed._

---

### Configuration Screen
![image](https://github.com/user-attachments/assets/4f9a4551-611a-458a-938f-5ca5f4c6e810)
![image](https://github.com/user-attachments/assets/b17fabbf-1784-4dee-83b1-118b12180590)

_Description: Customize your game settings, including opponent type (human or AI), game complexity, strategy, and heuristic._

---

### Instruction Screen
![image](https://github.com/user-attachments/assets/e12c8c6b-ed0f-4283-a947-fa4d7b3086be)

_Description: Displays the rules of Mancala, helping new players understand how to play._

---

### Game Screen
![image](https://github.com/user-attachments/assets/a42033d7-37d2-4dad-be42-c379bf26bc8d)

_Description: The main gameplay area. Players can interact with the pits and see their moves visualized._

---

## Installation

Follow these steps to set up and run the Mancala game on your local machine:

1. Clone the repository:
   ```bash
   git clone https://github.com/MohamedBarbych/Mancala-Game-With-Swing-Java.git
   cd Mancala-Gmae-With-Swing-Java

## Usage

### Home Screen
- Press the **"Continue"** button to navigate to the instruction screen.

### Instruction Screen
- Learn the rules of Mancala and proceed to the configuration screen.

### Configuration Screen
- Select the game settings (e.g., opponent type, strategy, and complexity).
- Press **"Start the Game"** to begin or **"Load Game"** to resume a saved game.

### Gameplay
- Interact with the pits by clicking them.
- Follow the turn sequence and enjoy the game!

---

## Game Rules

### Objective
The goal is to collect the most stones in your Mancala (storage pit) by the end of the game.

### Player Turns
- Players alternate turns.
- During your turn:
  - Select one of your pits (Player A: `A1`-`A6`, Player B: `B1`-`B6`).
  - Distribute stones in a counter-clockwise direction.

### Game Over
- The game ends when all pits on one side are empty.
- The player with the most stones in their Mancala wins.

---

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. **Fork** the repository.
2. **Create a new branch** for your feature:
   ```bash
   git checkout -b feature-name

## Commit Your Changes and Push Them

To commit your changes and push them to the repository, follow these steps:

```bash
git add .
git commit -m "Add new feature"
git push origin feature-name
