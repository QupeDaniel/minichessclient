package de.pki.minichess.game;

import de.pki.minichess.ai.PlayerRandomStateEval;
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
    public String runGame(String color, Client client) {
        State gameState = new State();
        Color playerColor = Color.BLACK;


        if (color.equals("W")) {
            playerColor = Color.WHITE;
        }

        PlayerRandomStateEval player = new PlayerRandomStateEval(playerColor);

        StringBuilder builder = new StringBuilder();
        boolean gameOver = false;
        Move nextMove;

        while (!gameOver) {
            System.out.println(gameState.getCurrentPlayer() + " zieht:\n");
            nextMove = null;
            if (player.color == gameState.getCurrentPlayer()) {
                nextMove = player.pickMove(gameState.getBoard());
                try {
                    client.sendMove(nextMove.getChessNotation());
                    System.out.println(nextMove.getChessNotation());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // An den Client gesendet
                builder.append(nextMove.getChessNotation() + "\n");
            } else {
                try {
                    nextMove = new Move(client.getMove());
                    System.out.println(nextMove.getChessNotation());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            gameOver = gameState.moveByMove(nextMove);

            builder.append("------------------\n");
            builder.append(gameState.getCurrentStateToString() + "\n");
        }
        return builder.toString();
    }

}
