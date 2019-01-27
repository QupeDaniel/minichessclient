package de.pki.minichess.game;

import de.pki.minichess.game.utils.PieceUtil;

/**
 * Holds the current state of a minichessgame
 */
public class State {

    private Board board;
    private int moveNumber;
    private Color currentPlayer;

    /**
     * Generate new State with initial settings
     */
    public State() {
        board = new Board();
        moveNumber = 1;
        currentPlayer = Color.WHITE;
    }

    /**
     * Getter for board
     *
     * @return
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Setter for board
     *
     * @param board
     */
    private void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Getter for MoveNumber
     *
     * @return
     */
    public int getMoveNumber() {
        return moveNumber;
    }

    /**
     * Setter for MoveNumber
     *
     * @param moveNumber
     */
    private void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }

    /**
     * Getter for CurrentPlayer
     *
     * @return
     */
    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns current state (board, player, moveNumber) as String
     *
     * @return
     */
    public String getCurrentStateToString() {
        StringBuilder currentState = new StringBuilder(moveNumber + " " + currentPlayer.getColorCode());
        currentState.append(board.toString());
        return currentState.toString();
    }

    /**
     * Set current state (board, player, moveNumber) by parsing a valid String
     *
     * @param stateString valid String of game state
     */
    public void setCurrentState(String stateString) {
        String[] lines = stateString.split("\n");

        char[][] newBoard = new char[6][5];

        String[] firstLineArgs = lines[0].split(" ");
        setMoveNumber(Integer.parseInt(firstLineArgs[0]));
        currentPlayer = Color.getColorByChar(firstLineArgs[1].charAt(0));

        for (int lineIndex = 1; lineIndex < 7; lineIndex++) {
            newBoard[lineIndex - 1] = lines[lineIndex].toCharArray();
        }
        setBoard(new Board(newBoard));
    }

    /**
     * Fulfills a move for a valid String
     *
     * @param move given move as String in format a1-b2
     */
    public void moveByString(String move) {
        if (isValidMoveString(move)) {
            String[] squares = move.split("-");
            Square squareFrom = new Square(squares[0]);
            Square squareTo = new Square(squares[1]);
            moveByMove(new Move(squareFrom, squareTo));
        }
    }

    /**
     * Fulfills a move for given move
     *
     * @param move given move
     * @return true if game over, false else
     */
    public boolean moveByMove(Move move) {

        char pieceToMove = board.getPieceByPosition(move.getFrom().getX(), move.getFrom().getY()).getChar();
        if (isValidStartPiece(pieceToMove) && MoveService.isMoveValid(move, board.getCharArray())) {
            if (checkForGameOver(move)) return true;
            if (canPieceBePromoted(pieceToMove, move.getTo().getY())) {
                pieceToMove = promotePawn(pieceToMove);
            }
            board.setPieceByPosition(move.getTo().getX(), move.getTo().getY(), pieceToMove);
            board.setPieceByPosition(move.getFrom().getX(), move.getFrom().getY(), '.');
            switchCurrentPlayer();
        }
        return false;
    }

    /**
     * Promotes a pawn to queen
     *
     * @param pieceToMove piece to promote
     * @return
     */
    private char promotePawn(char pieceToMove) {
        return (char) (((int) pieceToMove) + 1);
    }

    /**
     * Checks if pawn could be promoted to queen
     *
     * @param pieceToMove  piece to check for promotion
     * @param yDestination y destination position
     * @return
     */
    private boolean canPieceBePromoted(char pieceToMove, int yDestination) {
        if (Character.toLowerCase(pieceToMove) != 'p') { //only check for pawns
            return false;
        }
        if (currentPlayer == Color.BLACK && yDestination == 5) { // black pawn reaches end of board
            return true;
        }
        if (currentPlayer == Color.WHITE && yDestination == 0) { // white pawn reaches end of board
            return true;
        }
        return false;
    }

    /**
     * Switches current Player and increments moveNumber if necessary
     */
    private void switchCurrentPlayer() {
        if (currentPlayer == Color.WHITE) {
            currentPlayer = Color.BLACK;
        } else {
            setMoveNumber(getMoveNumber() + 1);
            currentPlayer = Color.WHITE;
        }
    }

    /**
     * Checks if start piece of move is valid
     *
     * @param pieceToMove start piece of a move
     * @return
     */
    private boolean isValidStartPiece(char pieceToMove) {
        if (currentPlayer != PieceUtil.getColorForPiece(pieceToMove))
            return false;
        if (pieceToMove == '.')
            return false;

        return true;
    }

    /**
     * Validates move string
     *
     * @param move string to validate
     * @return
     */
    private boolean isValidMoveString(String move) {
        return move.matches("([a-e][1-6]-[a-e][1-6])");
    }

    /**
     * checks if given move finishes the game
     * <p>
     * TODO: Should return the state (matt, unentschieden, ongoing) instead of a boolean.
     *
     * @param move
     * @return true if game over, false else
     */
    private boolean checkForGameOver(Move move) {
        Piece targetPiece = board.getPieceByPosition(move.getTo().getX(), move.getTo().getY());
        if (targetPiece.getFigure() == Figure.KING) {
            System.out.println("Schach-Matt - " + currentPlayer + " gewinnt");
            return true;
        }
        if ((moveNumber == 40) && (currentPlayer == Color.BLACK)) {
            System.out.println("Unentschieden");
            return true;
        }
        return false;
    }
}

