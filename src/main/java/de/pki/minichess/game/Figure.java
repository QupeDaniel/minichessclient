package de.pki.minichess.game;

public enum Figure {
    PAWN('p'), ROOK('r'), BISHOP('b'), KNIGHT('n'), QUEEN('q'), KING('k'), EMPTY('.');

    private final char figureCode;

    Figure(char figureCode) {
        this.figureCode = figureCode;
    }

    public char getFigureCode() {
        return figureCode;
    }

    public static Figure getFigureByChar(final char figureCode)
    {
        for (Figure type : Figure.values())
            if (type.figureCode == figureCode)
                return type;

        return null;
    }
}
