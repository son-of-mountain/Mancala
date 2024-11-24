# Mancala Game - Java Implementation

## Overview 👁

This project implements the traditional **Mancala** board game in Java, incorporating advanced features such as AI-based strategies, saved games, and an interactive interface for two-player or single-player modes. Mancala is a centuries-old African game that involves strategy and skill to capture the maximum number of stones.

<p align="center">
  <img src="https://github.com/user-attachments/assets/b393415f-7f74-4f90-911b-ef15dc1f34e0" alt="Mancala gameboard" width="400"/>
</p>

## Project Supervisor:
- Professor M'hamed AIT KBIR

## Table of Contents

1. [Screenshots](#screenshots)
2. [Features](#features)
3. [Game Rules](#game-rules)
4. [Technical Details](#technical-details)
5. [Installation](#installation)
6. [Credits](#made-in-tangier--by)


## Screenshots 📸
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


## Made in Tangier 😁 by
- <a href="https://github.com/ABAKHAR721" >Abdessamad Abakhar</a>
- <a href="https://github.com/MohamedBarbych">Mohamed Barbych</a>
- <a href="https://github.com/son-of-mountain/">Mouaad Elhansali</a>
