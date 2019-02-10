package de.pki.minichess.game;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PieceTest {

    @Test
    public void shouldCreateWhitePawnByChar() {
        Color expectedColor = Color.WHITE;
        Figure expectedFigure = Figure.PAWN;

        Piece piece = new Piece('P');

        assertThat(piece.getColor(), is(expectedColor));
        assertThat(piece.getFigure(), is(expectedFigure));
    }

    @Test
    public void shouldCreateBlackKnightByChar() {
        Color expectedColor = Color.BLACK;
        Figure expectedFigure = Figure.KNIGHT;

        Piece piece = new Piece('n');

        assertThat(piece.getColor(), is(expectedColor));
        assertThat(piece.getFigure(), is(expectedFigure));
    }

    @Test
    public void shouldCreateEmptySpaceByChar() {
        Color expectedColor = Color.EMPTY;
        Figure expectedFigure = Figure.EMPTY;

        Piece piece = new Piece('.');

        assertThat(piece.getColor(), is(expectedColor));
        assertThat(piece.getFigure(), is(expectedFigure));
    }
}
