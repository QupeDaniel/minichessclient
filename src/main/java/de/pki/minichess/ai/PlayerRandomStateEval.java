package de.pki.minichess.ai;

import de.pki.minichess.game.Color;
import de.pki.minichess.game.Move;
import de.pki.minichess.game.MoveService;
import de.pki.minichess.game.Square;
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
    public Move pickMove(char[][] board) {
        Vector<Square> currentPlayerPieces = scanPiecesForCurrentPlayer(board);
        Vector<Move> possibleMoves = new Vector<>();

        int scoreCurrentBoard = MoveService.scoreState(board, this.color);
        for (Square piece : currentPlayerPieces) {
            possibleMoves.addAll(MoveService.getPossibleMoves(piece.getX(), piece.getY(), board));
        }

        Move bestMove = possibleMoves.get(0);
        int bestScore = -100000;


        for (Move move : possibleMoves) {

            char[][] tempBoard = deepCopy(board);
            char pieceToMove = tempBoard[move.getFrom().getY()][move.getFrom().getX()];
            tempBoard[move.getTo().getY()][move.getTo().getX()] = pieceToMove;
            tempBoard[move.getFrom().getY()][move.getFrom().getX()] = '.';

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

    private char[][] deepCopy(char[][] board) {
        if (board == null) {
            return null;
        }

        final char[][] result = new char[board.length][];
        for (int i = 0; i < board.length; i++) {
            result[i] = Arrays.copyOf(board[i], board[i].length);
            // For Java versions prior to Java 6 use the next:
            // System.arraycopy(original[i], 0, result[i], 0, original[i].length);
        }
        return result;
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
