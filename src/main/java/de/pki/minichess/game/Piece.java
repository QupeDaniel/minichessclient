package de.pki.minichess.game;

import java.util.Objects;

public class Piece {
    private Figure figure;
    private Color color;
    private int score;


    public Piece(char pieceAsChar) {
        this.figure = Figure.getFigureByChar(Character.toLowerCase(pieceAsChar));
        this.color = getColorForPiece(pieceAsChar);
        this.score = getScorePerFigure(figure);
    }

    public int getScore() {
        return score;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Deprecated
    public char getChar() {
        if (color == Color.WHITE) {
            return Character.toUpperCase(figure.getFigureCode());
        }
        return figure.getFigureCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return figure == piece.figure &&
                color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(figure, color);
    }

    /**
     * Returns color for a given piece
     *
     * @param piece piece as char
     * @return color as char
     */
    private static Color getColorForPiece(char piece) {
        if ((piece >= 'A') && (piece <= 'Z'))
            return Color.WHITE;
        if ((piece >= 'a') && (piece <= 'z'))
            return Color.BLACK;
        return Color.EMPTY;
    }

    private static int getScorePerFigure(Figure figure) {
        int score = 0;
        switch (figure) {
            case PAWN:
                score = 100;
                break;
            case BISHOP:
                score = 300;
                break;
            case KNIGHT:
                score = 300;
                break;
            case ROOK:
                score = 500;
                break;
            case QUEEN:
                score = 900;
                break;
            case KING:
                score = 5000;
                break;
            case EMPTY:
                score = 0;
        }
        return score;
    }

    public void promote() {
        if (figure == Figure.PAWN){
            this.figure = Figure.QUEEN;
        }
    }
}
