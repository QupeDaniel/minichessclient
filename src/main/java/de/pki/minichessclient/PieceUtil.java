package de.pki.minichessclient;

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
    public static char getColorFromPosition(int xCord, int yCord, char[][] currentBoard) {
        return getColorForPiece(currentBoard[yCord][xCord]);
    }

    /**
     * Returns color for a given piece
     *
     * @param piece piece as char
     * @return color as char
     */
    public static char getColorForPiece(char piece) {
        if ((piece >= 'A') && (piece <= 'Z'))
            return 'W';
        if ((piece >= 'a') && (piece <= 'z'))
            return 'B';
        return 'e';
    }


}