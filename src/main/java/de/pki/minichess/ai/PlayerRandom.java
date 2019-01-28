package de.pki.minichess.ai;

import de.pki.minichess.ai.util.BoardScanner;
import de.pki.minichess.game.Board;
import de.pki.minichess.game.Color;
import de.pki.minichess.game.Move;

import java.util.Random;
import java.util.Vector;

/**
 * Implementation of Random AI Player
 */
public class PlayerRandom implements IPlayer {

    public Color color;
    private Random rand = new Random();

    /**
     * Generates a player with given color
     *
     * @param color color of the player
     */
    public PlayerRandom(Color color) {
        this.color = color;
    }

    @Override
    public Move pickMove(Board board) {
        Vector<Move> possibleMoves = BoardScanner.scanBoardForPlayerMoves(board, color);

        int nextMoveIndex = rand.nextInt(possibleMoves.size());
        return possibleMoves.get(nextMoveIndex);
    }
}
