package MancalaUI.Logic;

import java.util.Vector;

public class MancalaSearch extends GameSearch {
    private MancalaPosition position;

    public MancalaSearch(MancalaPosition position) {
        this.position = position;
    }

    @Override
    public boolean drawnPosition(Position p) {
        MancalaPosition pos = (MancalaPosition) p;
        return pos.isGameOver() && pos.getScore(0) == pos.getScore(1); // Égalité
    }

    @Override
    public boolean wonPosition(Position p, boolean player) {
        MancalaPosition pos = (MancalaPosition) p;
        int playerScore = pos.getScore(player ? 0 : 1);
        int opponentScore = pos.getScore(player ? 1 : 0);
        return pos.isGameOver() && playerScore > opponentScore;
    }

    @Override
    public float positionEvaluation(Position p, boolean player) {
        MancalaPosition pos = (MancalaPosition) p;
        return pos.evaluatePosition(); // Utilisation de la méthode dans MancalaPosition
    }

    @Override
    public Position[] possibleMoves(Position p, boolean player) {
        MancalaPosition pos = (MancalaPosition) p;
        Vector<Position> moves = new Vector<>();

        // Generate moves only for the correct player's pits
        int startPit = player ? 7 : 0; // Player B pits start at 7, Player A pits start at 0
        int endPit = player ? 13 : 6; // Player B pits end at 12, Player A pits end at 5

        for (int i = startPit; i < endPit; i++) {
            if (pos.getBoard()[i] > 0) { // Check if pit has stones
                MancalaPosition cloned = (MancalaPosition) pos.clone();
                cloned.makeMove(new MancalaMove(i));
                moves.add(cloned);
            }
        }

        return moves.toArray(new Position[0]);
    }


    @Override
    public Position makeMove(Position p, boolean player, Move move) {
        MancalaPosition pos = (MancalaPosition) p;
        MancalaPosition newPos = (MancalaPosition) pos.clone();
        newPos.makeMove(move);
        return newPos;
    }

    @Override
    public boolean reachedMaxDepth(Position p, int depth) {
        return depth >= getComplexity();
    }

    /**
     * Méthode publique pour accéder à alphaBeta
     */
    public Vector<Object> performAlphaBeta(Position p, boolean player) {
        return alphaBeta(0, p, player); // Appelle la méthode protégée
    }
}
