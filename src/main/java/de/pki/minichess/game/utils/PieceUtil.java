package de.pki.minichess.game.utils;

import de.pki.minichess.game.Color;

/**
 * Helper Class to support piececolor detection.
 */
public class PieceUtil {

    /**
     * Returns color for a given position on a given board
     *
     * @param xCord        x position
     * @param yCord        y position
     * @param currentBoard current state of board
     * @return color as char
     */
    public static Color getColorFromPosition(int xCord, int yCord, char[][] currentBoard) {
        return getColorForPiece(currentBoard[yCord][xCord]);
    }

    /**
     * Returns color for a given piece
     *
     * @param piece piece as char
     * @return color as char
     */
    public static Color getColorForPiece(char piece) {
        if ((piece >= 'A') && (piece <= 'Z'))
            return Color.WHITE;
        if ((piece >= 'a') && (piece <= 'z'))
            return Color.BLACK;
        return Color.EMPTY;
    }


    public static int getScorePerPieceAndPlayerColor(char piece, Color currentPlayer) {
        int score = 0;
        score = getScorePerPiece(piece);
        if (getColorForPiece(piece) != currentPlayer) {
            score *= -1;
        }
        return score;
    }

    private static int getScorePerPiece(char piece) {
        int score = 0;
        switch (Character.toLowerCase(piece)) {
            case 'p':
                score = 100;
                break;
            case 'b':
                score = 300;
                break;
            case 'n':
                score = 300;
                break;
            case 'r':
                score = 500;
                break;
            case 'q':
                score = 900;
                break;
            case 'k':
                score = 5000;
                break;
        }
        return score;
    }
}
