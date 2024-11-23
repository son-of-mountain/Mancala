# Mancala Game - Java Implementation

## Overview 👁

This project implements the traditional **Mancala** board game in Java, incorporating advanced features such as AI-based strategies, saved games, and an interactive interface for two-player or single-player modes. Mancala is a centuries-old African game that involves strategy and skill to capture the maximum number of stones.

<p align="center">
  <img src="https://github.com/user-attachments/assets/b393415f-7f74-4f90-911b-ef15dc1f34e0" alt="Mancala gameboard" width="400"/>
</p>

## Features ⭐

- **Two-player mode**: Play with a friend locally.
- **Single-player mode**: Compete against a machine using intelligent strategies.
- **AI assistance**: Request hints during the game (limited number of times).
- **Game saving and resuming**: Save your game progress and continue later.
- **Replay functionality**: Review saved games to analyze strategies.
- **Customizable difficulty**: Adjust the AI's complexity to suit your skill level.
- **Multiple heuristics**: Implements at least two AI heuristics for enhanced gameplay.

## Game Rules 🕹️

1. **Game Board**:
   - The board consists of two rows of six small pits, with a larger "store" pit on each side.
   - Players aim to collect the most stones in their store pit.

2. **Turn Mechanics**:
   - Players select one of their pits and distribute its stones clockwise, one at a time.
   - Stones can be placed in the player’s store but not in the opponent's store.
   - Captures occur if the last stone lands in an empty pit on the player’s side.

3. **Special Cases**:
   - Players earn an extra turn if their last stone lands in their store.
   - The game ends when all pits on one side are empty. Remaining stones go to the respective store pits.

4. **Winning**:
   - The player with the most stones in their store wins.

## Technical Details 🔎

### Algorithms
- Implements adversarial search algorithms like **Minimax** and **Alpha-Beta Pruning** for the AI.
- Two distinct **heuristics** are available to evaluate game states and enhance the AI's decisions.

### Java Design
- **Object-Oriented Programming**:
  - Respects principles like inheritance and encapsulation.
  - Utilizes a modular design for board, player, and AI components.
- **Alpha-Beta Pruning**:
  - Customized implementation replacing `alphaBetaHelper(...)` based on course requirements.

### File Management
- Games can be saved and resumed using a structured file format.
- Saved games can be replayed for analysis.

## Installation 📥

1. Clone the repository:
   ```bash
   git clone https://github.com/son-of-mountain/mancala-game.git
   cd mancala-game

