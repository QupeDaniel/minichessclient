package de.pki.minichess.game;

import java.util.Arrays;

public class Board {
    private Piece[][] board;
    private int scoreBlack;
    private int scoreWhite;

    Board() {
        initializeBoard();
        calculateScoreForBoard();
    }

    public Board(char[][] newBoard) {
        this.board = new Piece[6][5];
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 5; column++) {
                this.board[row][column] = new Piece(newBoard[row][column]);
            }
        }
        calculateScoreForBoard();
    }

    public Board(Board board) {
        Piece[][] tempBoard = board.getBoard();
        this.board = deepCopy(tempBoard);

    }

    public int getScoreBlack() {
        return scoreBlack;
    }

    public int getScoreWhite() {
        return scoreWhite;
    }

    public Piece getPieceByPosition(int x, int y) {
        return board[y][x];
    }

    public String toString() {
        StringBuilder stringBuilderBoard = new StringBuilder();
        for (int row = 0; row < 6; row++) {
            stringBuilderBoard.append("\n");
            for (int column = 0; column < 5; column++) {
                stringBuilderBoard.append(board[row][column].getChar());
            }
        }
        return stringBuilderBoard.toString();
    }

    public void setPieceByPosition(int x, int y, Piece pieceToSet) {
        board[y][x] = pieceToSet;
    }

    public Piece[][] getBoard() {
        return board;
    }

    private Piece[][] deepCopy(Piece[][] board) {
        if (board == null) {
            return null;
        }

        final Piece[][] result = new Piece[board.length][];
        for (int i = 0; i < board.length; i++) {
            result[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return result;
    }

    private void initializeBoard() {
        board = new Piece[][]{
                {new Piece('k'), new Piece('q'), new Piece('b'), new Piece('n'), new Piece('r')},
                {new Piece('p'), new Piece('p'), new Piece('p'), new Piece('p'), new Piece('p')},
                {new Piece('.'), new Piece('.'), new Piece('.'), new Piece('.'), new Piece('.')},
                {new Piece('.'), new Piece('.'), new Piece('.'), new Piece('.'), new Piece('.')},
                {new Piece('P'), new Piece('P'), new Piece('P'), new Piece('P'), new Piece('P')},
                {new Piece('R'), new Piece('N'), new Piece('B'), new Piece('Q'), new Piece('K')}
        };
    }

    private void calculateScoreForBoard() {
        scoreBlack = 0;
        scoreWhite = 0;
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 5; column++) {
                Piece pieceToScore = this.board[row][column];
                switch (pieceToScore.getColor()) {
                    case WHITE:
                        scoreWhite = +pieceToScore.getScore();
                        break;
                    case BLACK:
                        scoreBlack = +pieceToScore.getScore();
                        break;
                }
            }
        }
    }
}
