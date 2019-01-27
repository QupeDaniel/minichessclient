package de.pki.minichess.game.utils;

import de.pki.minichess.game.Color;
import de.pki.minichess.game.Piece;

/**
 * Helper Class to support piececolor detection.
 */
public class PieceUtil {

    public static int getScorePerPieceAndPlayerColor(Piece piece, Color currentPlayer) {
        int score = 0;
        score = piece.getScore();
        if (piece.getColor() != currentPlayer) {
            score = score * (-1);
        }
        return score;
    }
}
