package controllers;

import common.messages.GameExitMessage;
import common.messages.GameJoinMessage;
import common.messages.GameMoveMessage;
import common.messages.GameOpenMessage;
import networking.GameServer;
import networking.PlayerConnection;

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

    public void onGameJoinMessage(GameJoinMessage message, PlayerConnection playerConnection) {
        Game game = getByGameId(message.gameId);
        boolean addPlayerSuccess;

        if (game == null) {
            // Create new Game
            game = new Game();
            game.addPlayer(playerConnection);
            addPlayerSuccess = games.add(game);
            playerConnection.log("Creating game " + game.state.gameId);
        } else {
            playerConnection.log("Joining game " + game.state.gameId);
            // Add player to game
            addPlayerSuccess = game.addPlayer(playerConnection);
        }

        // Open Game on client
        if (addPlayerSuccess) {
            GameOpenMessage gameOpenMessage = new GameOpenMessage();
            gameOpenMessage.gameId = game.state.gameId;
            playerConnection.sendTCP(gameOpenMessage);
            playerConnection.log("Opening game " + game.state.gameId + " on client");
        }

        // If game is full, start it
        if (game.isFull()) {
            game.start();
        }

        // Send lobby State
        GameServer.instance.lobby.sendLobbyState();
    }

    public void onGameExitMessage(GameExitMessage message, PlayerConnection playerConnection) {
        playerConnection.log("Exiting game " + message.gameId);
        Game game = getByGameId(message.gameId);
        if (game != null) {
            game.removePlayer(playerConnection);
            if (game.isEmpty()) {
                games.remove(game);
            } else {
                game.reset();
            }
        }
        GameServer.instance.lobby.sendLobbyState();
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

    public Game getGameByPlayerConnection(PlayerConnection playerConnection) {
        for (Game game : games) {
            if (game.hasPlayerConnected(playerConnection)) {
                return game;
            }
        }
        return null;
    }

    public void onGameMoveMessage(GameMoveMessage message, PlayerConnection playerConnection) {
        Game game = getGameByPlayerConnection(playerConnection);
        if (game != null) {
            game.onGameMoveMessage(message, playerConnection);
        }
    }
}