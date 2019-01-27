package de.pki.minichess.game;

import de.pki.minichess.game.utils.PieceUtil;

import java.util.Objects;

public class Piece {
    private Figure figure;
    private Color color;


    Piece(char pieceAsChar) {
        this.figure = Figure.getFigureByChar(Character.toLowerCase(pieceAsChar));
        this.color = PieceUtil.getColorForPiece(pieceAsChar);
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
}
