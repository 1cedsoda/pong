package controllers;

import com.esotericsoftware.kryonet.Connection;
import common.messages.GameJoinMessage;
import common.messages.GameOpenMessage;
import server.GameServer;
import server.PlayerConnection;

import java.util.ArrayList;
import java.util.List;

public class Games {
    public List<Game> games;

    public Games() {
        this.games = new ArrayList<Game>();
    }

    public Game getByGameId(String gameId) {
        for (Game game : games) {
            if (game.state.gameId.equals(gameId)) {
                return game;
            }
        }
        return null;
    }

    public void onGameJoinMessage(GameJoinMessage message, PlayerConnection playerConnection)
    {
        System.out.println("onGameJoinMessage");
        Game game = getByGameId(message.gameId);
        boolean addPlayerSuccess;

        if (game == null) {
            // Create new Game
            game = new Game();
            game.addPlayer(playerConnection);
            addPlayerSuccess = games.add(game);
        } else {
            // Add player to game
            addPlayerSuccess = game.addPlayer(playerConnection);
        }


        // Open Game on client
        if (addPlayerSuccess) {
            System.out.println("Player joined game " + game.state.gameId);
            GameOpenMessage gameOpenMessage = new GameOpenMessage();
            gameOpenMessage.gameId = game.state.gameId;
            playerConnection.sendTCP(gameOpenMessage);
        }
    }

    // get game by gameId
    private Game getGameByGameId(String gameId) {
        for (Game game : games) {
            if (game.state.gameId.equals(gameId)) {
                return game;
            }
        }
        return null;
    }
}