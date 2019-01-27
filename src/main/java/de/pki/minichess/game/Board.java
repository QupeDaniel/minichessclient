package de.pki.minichess.game;

public class Board {
    private Piece[][] board;

    Board() {
        initializeBoard();
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

    public Board(char[][] newBoard) {
        this.board = new Piece[6][5];
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 5; column++) {
                this.board[row][column] = new Piece(newBoard[row][column]);
            }
        }
    }

    public Piece getPieceByPosition(int x, int y) {
        return board[y][x];
    }

    public String toString(){
        StringBuilder stringBuilderBoard = new StringBuilder();
        for (int row = 0; row < 6; row++) {
            stringBuilderBoard.append("\n");
            for (int column = 0; column < 5; column++) {
                stringBuilderBoard.append(board[row][column].getChar());
            }
        }
        return stringBuilderBoard.toString();
    }

    @Deprecated
    public char[][] getCharArray() {
        char[][] boardAsChar = new char[6][5];
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 5; column++) {
                boardAsChar[row][column] = board[row][column].getChar();
            }
        }
        return boardAsChar;
    }

    @Deprecated
    public void setPieceByPosition(int x, int y, char pieceToMove) {
        Piece pieceToSet = new Piece(pieceToMove);
        board[y][x] = pieceToSet;
    }

    public Piece[][] getBoard() {
        return board;
    }
}
