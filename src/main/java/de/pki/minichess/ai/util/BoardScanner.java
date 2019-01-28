package de.pki.minichess.ai.util;

import de.pki.minichess.game.*;

import java.util.Vector;

public class BoardScanner {

    public static Vector<Move> scanBoardForPlayerMoves(Board board, Color color) {
        Vector<Square> ownedPieces = scanBoardForPlayerPieces(board, color);

        Vector<Move> possibleMoves = new Vector<>();
        for (Square piece : ownedPieces) {
            possibleMoves.addAll(MoveService.getPossibleMoves(piece.getX(), piece.getY(), board));
        }
        return possibleMoves;
    }

    private static Vector<Square> scanBoardForPlayerPieces(Board board, Color color) {
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
