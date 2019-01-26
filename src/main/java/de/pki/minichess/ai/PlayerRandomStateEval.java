package de.pki.minichess.ai;

import de.pki.minichess.game.*;
import de.pki.minichess.game.utils.PieceUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class PlayerRandomStateEval implements IPlayer {

    private Color color;
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
    public Move pickMove(char[][] board) {
        Vector<Square> currentPlayerPieces = scanPiecesForCurrentPlayer(board);
        Vector<Move> possibleMoves = new Vector<>();

        int scoreCurrentBoard = MoveService.scoreState(board, this.color);
        for (Square piece : currentPlayerPieces) {
            possibleMoves.addAll(MoveService.getPossibleMoves(piece.getX(), piece.getY(), board));
        }

        Move bestMove = possibleMoves.get(0);
        int bestScore = -1000000;

        for (Move move : possibleMoves) {
            State currentState = new State();
            currentState.setBoard(board);
            currentState.moveByMove(move);
            char[][] nextBoard = currentState.getBoard();
            int score = MoveService.scoreState(nextBoard, this.color);

            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private Vector<Square> scanPiecesForCurrentPlayer(char[][] board) {
        Vector<Square> ownedPieces = new Vector<>();
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 5; column++) {
                if (PieceUtil.getColorForPiece(board[row][column]) == color) {
                    ownedPieces.add(new Square(column, row));
                }
            }
        }
        return ownedPieces;
    }
}
