package de.pki.minichess.ai;

import de.pki.minichess.game.*;
import de.pki.minichess.game.utils.PieceUtil;

import java.util.Arrays;
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
        Vector<Square> currentPlayerPieces = scanPiecesForCurrentPlayer(board);
        Vector<Move> possibleMoves = new Vector<>();

        for (Square piece : currentPlayerPieces) {
            possibleMoves.addAll(MoveService.getPossibleMoves(piece.getX(), piece.getY(), board));
        }

        Move bestMove = possibleMoves.get(0);
        int bestScore = -100000;


        for (Move move : possibleMoves) {

            Board tempBoard = new Board(board);
            Piece pieceToMove = tempBoard.getPieceByPosition(move.getFrom().getX(), move.getFrom().getY());
            tempBoard.setPieceByPosition(move.getTo().getX(), move.getTo().getY(), pieceToMove);
            tempBoard.setPieceByPosition(move.getFrom().getX(), move.getFrom().getY(), new Piece('.'));

            int score = MoveService.scoreState(tempBoard, this.color);
            System.out.println("Move " + move.getChessNotation() + " bringt " + score);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        System.out.println("\n");
        return bestMove;
    }

    private Vector<Square> scanPiecesForCurrentPlayer(Board board) {
        Vector<Square> ownedPieces = new Vector<>();
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 5; column++) {
                if (board.getPieceByPosition(column, row).getColor() == color) {
                    ownedPieces.add(new Square(column, row));
                }
            }
        }
        return ownedPieces;
    }
}
