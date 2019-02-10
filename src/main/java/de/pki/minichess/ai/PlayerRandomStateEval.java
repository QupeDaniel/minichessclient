package de.pki.minichess.ai;

import de.pki.minichess.ai.util.BoardScanner;
import de.pki.minichess.game.Board;
import de.pki.minichess.game.Color;
import de.pki.minichess.game.Move;
import de.pki.minichess.game.Piece;

import java.util.Random;
import java.util.Vector;

public class PlayerRandomStateEval implements IPlayer {

    public Color color;
    private Random rand = new Random();

    /**
     * Generates a player with given color
     *
     * @param color color of the player
     */
    public PlayerRandomStateEval(Color color) {
        this.color = color;
    }

    @Override
    public Move pickMove(Board board) {

        Vector<Move> possibleMoves = BoardScanner.scanBoardForPlayerMoves(board, color);

        Move bestMove = possibleMoves.get(0);
        int bestScore = -100000;

        for (Move move : possibleMoves) {

            Board tempBoard = new Board(board);
            Piece pieceToMove = tempBoard.getPieceByPosition(move.getFrom().getX(), move.getFrom().getY());
            tempBoard.setPieceByPosition(move.getTo().getX(), move.getTo().getY(), pieceToMove);
            tempBoard.setPieceByPosition(move.getFrom().getX(), move.getFrom().getY(), new Piece('.'));

            int score = 0;

            switch (color) {
                case WHITE:
                    score = tempBoard.getScoreWhite() - tempBoard.getScoreBlack();
                    break;
                case BLACK:
                    score = tempBoard.getScoreBlack() - tempBoard.getScoreWhite();
                    break;
            }
            System.out.println("Move " + move.getChessNotation() + " bringt " + score);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        System.out.println("\n");
        return bestMove;
    }
}
