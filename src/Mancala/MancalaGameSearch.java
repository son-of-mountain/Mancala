package Mancala;

import java.util.Arrays;
import java.util.Vector;

public class MancalaGameSearch extends GameSearch {

    @Override
    public boolean drawnPosition(Position p) {
        MancalaPosition position = (MancalaPosition) p;
        return Arrays.stream(position.getBoard()).sum() == 0;
    }

    @Override
    public boolean wonPosition(Position p, boolean player) {
        MancalaPosition position = (MancalaPosition) p;
        int playerStore = player ? position.getBoard()[6] : position.getBoard()[13];
        return playerStore > 24; // Example win condition
    }

//    @Override
//    public float positionEvaluation(Position p, boolean player) {
//        MancalaPosition position = (MancalaPosition) p;
//        int playerStore = player ? position.getBoard()[6] : position.getBoard()[13];
//        int opponentStore = player ? position.getBoard()[13] : position.getBoard()[6];
//        return playerStore - opponentStore;
//    }


    @Override
    public Position[] possibleMoves(Position p, boolean player) {
        MancalaPosition position = (MancalaPosition) p;
        int[] board = position.getBoard();
        int start = player ? 0 : 7;
        int end = player ? 5 : 12;

        Vector<Position> moves = new Vector<>();
        for (int i = start; i <= end; i++) {
            if (board[i] > 0) {
                MancalaPosition newPosition = (MancalaPosition) position.clone();
                newPosition.makeMove(new MancalaMove(i));
                moves.add(newPosition);
            }
        }
        return moves.toArray(new Position[0]);
    }

    @Override
    public Position makeMove(Position p, boolean player, Move move) {
        MancalaPosition position = (MancalaPosition) p;
        MancalaMove mancalaMove = (MancalaMove) move;
        position.makeMove(mancalaMove);
        return position;
    }




    @Override
    public float positionEvaluation(Position p, boolean player) {
        MancalaPosition position = (MancalaPosition) p;
        return position.evaluatePosition(); // Directly use evaluatePosition for scoring
    }

    @Override
    public boolean reachedMaxDepth(Position p, int depth) {
        MancalaPosition position = (MancalaPosition) p;
        return depth >= position.getComplexity(); // Respect user-defined complexity
    }


}