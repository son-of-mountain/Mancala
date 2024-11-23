package Mancala;

import java.util.Vector;

public abstract class GameSearch {
    public static final boolean DEBUG = false;

    public static final boolean PROGRAM = true;
    public static final boolean HUMAN = false;

    private int complexity = 3; // Default complexity (AI depth)
    private String strategy = "offensive"; // Default strategy

    public abstract boolean drawnPosition(Position p);

    public abstract boolean wonPosition(Position p, boolean player);

    public abstract float positionEvaluation(Position p, boolean player);

    public abstract Position[] possibleMoves(Position p, boolean player);

    public abstract Position makeMove(Position p, boolean player, Move move);

    public abstract boolean reachedMaxDepth(Position p, int depth);

    protected Vector alphaBeta(int depth, Position p, boolean player) {
        return alphaBetaHelper(depth, p, player, -1000000.0f, 1000000.0f);
    }

    protected Vector alphaBetaHelper(int depth, Position p, boolean player, float alpha, float beta) {
        if (reachedMaxDepth(p, depth)) {
            Vector v = new Vector(2);
            v.addElement(positionEvaluation(p, player));
            v.addElement(null);
            return v;
        }

        Position[] moves = possibleMoves(p, player);
        Vector best = new Vector();
        for (Position move : moves) {
            Vector v = alphaBetaHelper(depth + 1, move, !player, -beta, -alpha);
            float value = -((Float) v.elementAt(0));
            if (value > alpha) {
                alpha = value;
                best.clear();
                best.add(move);
            }
            if (alpha >= beta) break;
        }

        Vector v2 = new Vector();
        v2.addElement(alpha);
        v2.addAll(best);
        return v2;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy.toLowerCase();
    }

    public int getComplexity() {
        return complexity;
    }

    public String getStrategy() {
        return strategy;
    }
}
