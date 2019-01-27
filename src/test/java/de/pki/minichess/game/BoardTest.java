package de.pki.minichess.game;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BoardTest {

    @Test
    public void shouldGetInitialBoard() {
        Piece[] expectedFirstLine = {new Piece('k'), new Piece('q'), new Piece('b'), new Piece('n'), new Piece('r')};
        Piece[] expectedSecondLine = {new Piece('p'), new Piece('p'), new Piece('p'), new Piece('p'), new Piece('p')};
        Piece[] expectedThirdAndForthLine = {new Piece('.'), new Piece('.'), new Piece('.'), new Piece('.'), new Piece('.')};
        Piece[] expectedFithLine = {new Piece('P'), new Piece('P'), new Piece('P'), new Piece('P'), new Piece('P')};
        Piece[] expectedSixthLine = {new Piece('R'), new Piece('N'), new Piece('B'), new Piece('Q'), new Piece('K')};

        Piece[][] expectedBoard = {
                expectedFirstLine,
                expectedSecondLine,
                expectedThirdAndForthLine,
                expectedThirdAndForthLine,
                expectedFithLine,
                expectedSixthLine
        };

        Board board = new Board();

        assertThat(board.getBoard(), is(expectedBoard));
    }

    @Test
    public void shouldSetBoardByCharArray() {
        char[][] input = {{'k', 'q', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p'},
                {'.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', 'P'},
                {'P', 'P', 'P', 'P', '.'},
                {'R', 'N', 'B', 'Q', 'K'}};

        Piece[][] expectedBoard = {
                {new Piece('k'), new Piece('q'), new Piece('b'), new Piece('n'), new Piece('r')},
                {new Piece('p'), new Piece('p'), new Piece('p'), new Piece('p'), new Piece('p')},
                {new Piece('.'), new Piece('.'), new Piece('.'), new Piece('.'), new Piece('.')},
                {new Piece('.'), new Piece('.'), new Piece('.'), new Piece('.'), new Piece('P')},
                {new Piece('P'), new Piece('P'), new Piece('P'), new Piece('P'), new Piece('.')},
                {new Piece('R'), new Piece('N'), new Piece('B'), new Piece('Q'), new Piece('K')}
        };


        Board board = new Board(input);

        assertThat(board.getBoard(), is(expectedBoard));
    }

}
