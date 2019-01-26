package de.pki.minichess.game;

import de.pki.minichess.ai.PlayerRandom;
import de.pki.minichess.client.connector.Client;

import java.io.IOException;

/**
 * Controls the automatic game processing.
 * <p>
 * A good point to insert the KI.
 */
public class GameController {


    /**
     * Executes an automatically played game.
     * KI should be inserted here.
     *
     * @return The game steps until game finishes.
     */
    public void runGame(String color, Client client) {
        State gameState = new State();
        Color playerColor = Color.BLACK;


        if (color.equals("W")) {
            playerColor = Color.WHITE;
        }

        PlayerRandom player = new PlayerRandom(playerColor);

//        StringBuilder builder = new StringBuilder();
        boolean gameOver = false;
        Move nextMove = null;

        while (!gameOver) {
            if (player.color == gameState.getCurrentPlayer()) {
                nextMove = player.pickMove(gameState.getBoard());
                try {
                    System.out.println(nextMove.getChessNotation());
                    client.sendMove(nextMove.getChessNotation());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // An den Client gesendet
//            builder.append(nextMove + "\n");
            } else if (player.color != gameState.getCurrentPlayer()) {
                try {
                    nextMove = new Move(client.getMove());// Client get move
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            gameOver = gameState.moveByMove(nextMove);

//            builder.append("------------------\n");
//            builder.append(gameState.getCurrentStateToString() + "\n");
        }
//        return builder.toString();
    }

}
