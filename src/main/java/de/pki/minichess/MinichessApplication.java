package de.pki.minichess;

import de.pki.minichess.client.ui.Console;

import java.io.IOException;

/**
 * Application class to run a game of minichess
 */
public class MinichessApplication {

    private boolean locale = true;

    /**
     * Initializes two players with given ai and plays a game of minichess
     * dirty loop stops if a move finishes the game
     *
     * @param args
     */
    public static void main(String[] args) {


//        State gameState = new State();
//        PlayerRandom whitePlayer = new PlayerRandom(Color.WHITE);
//        PlayerRandomStateEval blackPlayer = new PlayerRandomStateEval(Color.BLACK);
//
//        boolean gameOver = false;
//
//        while (!gameOver) {
//            Move nextMove = null;
//
//            if (gameState.getCurrentPlayer() == Color.WHITE) {
//                nextMove = whitePlayer.pickMove(gameState.getBoard());
//            } else if (gameState.getCurrentPlayer() == Color.BLACK) {
//                nextMove = blackPlayer.pickMove(gameState.getBoard());
//            }
//            gameOver = gameState.moveByMove(nextMove);
//
//            System.out.println(nextMove.getChessNotation());
//            System.out.println("------------------\n");
//            System.out.println(gameState.getCurrentStateToString() + "\n");
//        }

        try {
            Console console = new Console();
            boolean goOn = true;
            while (goOn) {
                try {
                    goOn = console.requestCommand();
                } catch (RuntimeException e) {
                    // It's strange but the Client throws RuntimeExceptions as business exceptions. So we just handle them
                    // smoothly by printing out the message and keeping the application running.
                    System.out.println("ERROR: " + e.getMessage());
                }

            }
        } catch (IOException e) {
            System.out.println("SOMETHING WENT TERRIBLY WRONG!!! :-(");
            e.printStackTrace();
        }


    }
}