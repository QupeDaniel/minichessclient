package de.pki.minichess.game;

import de.pki.minichess.game.utils.PieceUtil;

import java.util.Vector;

/**
 * Service to get possible moves or validate moves.
 */
public class MoveService {


    private enum Capture {
        TRUE, FALSE, ONLY
    }

    /**
     * Checks if a given move is valid on a given board.
     *
     * @param move  move to validate
     * @param board state of board to validate
     * @return true if move is valid else false
     */
    public static boolean isMoveValid(Move move, Board board) {
        Vector<Move> moves = getPossibleMoves(move.getFrom().getX(), move.getFrom().getY(), board);
        for (Move moveItem : moves) {
            if (moveItem.equals(move))
                return true;
        }
        return false;
    }

    /**
     * Gets piece of given position and scans for possible moves.
     *
     * @param xStart       x start position
     * @param yStart       y start position
     * @param currentBoard state of the current board
     * @return vector of possible moves
     */
    public static Vector<Move> getPossibleMoves(int xStart, int yStart, Board currentBoard) {
        Vector<Move> moves = new Vector<>();

        Piece currentPiece = currentBoard.getPieceByPosition(xStart, yStart);
        Color color = currentPiece.getColor();
        Capture capture = Capture.TRUE;

        switch (currentPiece.getFigure()) {
            case KING:
                moves.addAll(symmscan(xStart, yStart, 0, 1, capture, true, currentBoard));
                moves.addAll(symmscan(xStart, yStart, 1, 1, capture, true, currentBoard));
                break;
            case QUEEN:
                moves.addAll(symmscan(xStart, yStart, 0, 1, capture, false, currentBoard));
                moves.addAll(symmscan(xStart, yStart, 1, 1, capture, false, currentBoard));
                break;
            case BISHOP:
                moves.addAll(symmscan(xStart, yStart, 1, 1, capture, false, currentBoard));
                capture = Capture.FALSE;
                moves.addAll(symmscan(xStart, yStart, 0, 1, capture, true, currentBoard));
                break;
            case ROOK:
                moves.addAll(symmscan(xStart, yStart, 0, 1, capture, false, currentBoard));
                break;
            case KNIGHT:
                moves.addAll(symmscan(xStart, yStart, 1, 2, capture, true, currentBoard));
                moves.addAll(symmscan(xStart, yStart, -1, 2, capture, true, currentBoard));
                break;
            case PAWN:
                int direction = 1;
                if (color == Color.WHITE) {
                    direction = -1;
                }
                moves.addAll(moveScan(xStart, yStart, -1, direction, Capture.ONLY, true, currentBoard));
                moves.addAll(moveScan(xStart, yStart, 1, direction, Capture.ONLY, true, currentBoard));
                moves.addAll(moveScan(xStart, yStart, 0, direction, Capture.FALSE, true, currentBoard));
                break;
        }
        return moves;
    }

    public static int scoreState(Board board, Color playerColor) {
        int score = 0;
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 5; column++)
                score = score + PieceUtil.getScorePerPieceAndPlayerColor(board.getPieceByPosition(column, row), playerColor);
        }
        return score;
    }


    /**
     * Scans for possible moves in all four rotational symetries.
     * Example:
     * xDirection = 1; yDirection = 1;
     * 1. ( 1,  1)
     * 2. ( 1, -1)
     * 3. (-1, -1)
     * 4. ( 1. -1)
     *
     * @param xStart       x start position
     * @param yStart       y start position
     * @param xDirection   x direction of move
     * @param yDirection   y direction of move
     * @param capture      caputre flag ("true", "false" and "only")
     * @param stopShort    if true stops after one iteration
     * @param currentBoard state of the current board
     * @return vector of possible moves
     */
    private static Vector<Move> symmscan(int xStart, int yStart, int xDirection, int yDirection, Capture capture, boolean stopShort, Board currentBoard) {
        Vector<Move> moves = new Vector<>();
        for (int i = 0; i < 4; i++) {
            moves.addAll(moveScan(xStart, yStart, xDirection, yDirection, capture, stopShort, currentBoard));

            //exchange directions
            int tmpDirection = xDirection;
            xDirection = yDirection;
            yDirection = tmpDirection * -1;
        }
        return moves;
    }

    /**
     * Scans for possible moves with given params.
     *
     * @param xStart       x start position
     * @param yStart       y start position
     * @param xDirection   x direction of move
     * @param yDirection   y direction of move
     * @param capture      caputre flag
     * @param stopShort    if true stops after one iteration
     * @param currentBoard state of the current board
     * @return vector of possible moves
     */
    private static Vector<Move> moveScan(int xStart, int yStart, int xDirection, int yDirection, Capture capture, boolean stopShort, Board currentBoard) {
        int xDest = xStart;
        int yDest = yStart;
        Color color = currentBoard.getPieceByPosition(xStart, yStart).getColor();
        Vector<Move> moves = new Vector<>();
        do {
            xDest += xDirection;
            yDest += yDirection;
            if (xDest >= 5 || xDest < 0 || yDest >= 6 || yDest < 0) {
                break;
            }
            Color destinationColor = currentBoard.getPieceByPosition(xDest, yDest).getColor();
            if (destinationColor != Color.EMPTY) {// if target is not empty
                if (destinationColor == color)
                    break;
                if (capture == Capture.FALSE)
                    break;
                stopShort = true;

            } else if (capture == Capture.ONLY)
                break;
            moves.add(new Move(new Square(xStart, yStart), new Square(xDest, yDest)));
        } while (!stopShort);
        return moves;
    }
}
