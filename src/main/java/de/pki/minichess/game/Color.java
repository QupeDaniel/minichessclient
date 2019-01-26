package de.pki.minichess.game;

public enum Color {
    WHITE('W'), BLACK('B'), EMPTY('e');

    private final char colorCode;

    Color(char colorCode) {
        this.colorCode = colorCode;
    }

    public char getColorCode() {
        return colorCode;
    }

    public static Color getColorByChar(final char colorCode)
    {
        for (Color type : Color.values())
            if (type.colorCode == colorCode)
                return type;

        return null;
    }

}
