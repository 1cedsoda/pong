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
        System.out.println("onGameJoinMessage");
        Game game = getByGameId(message.gameId);
        boolean addPlayerSuccess;

        if (game == null) {
            System.out.println("Game not found, creating...");
            // Create new Game
            game = new Game();
            game.addPlayer(playerConnection);
            addPlayerSuccess = games.add(game);
        } else {
            System.out.println("Game found, joining...");
            // Add player to game
            addPlayerSuccess = game.addPlayer(playerConnection);
        }

        // Open Game on client
        if (addPlayerSuccess) {
            System.out.println("Player joined game " + game.state.gameId);
            GameOpenMessage gameOpenMessage = new GameOpenMessage();
            gameOpenMessage.gameId = game.state.gameId;
            System.out.println("Sending game open message to client " + gameOpenMessage.gameId);
            playerConnection.sendTCP(gameOpenMessage);
        }

        // If game is full, start it
        if (game.isFull()) {
            System.out.println("Game is full, starting...");
            game.start();
        }

        // Send lobby State
        GameServer.instance.lobby.sendLobbyState();
    }

    public void onGameExitMessage(GameExitMessage message, PlayerConnection playerConnection) {
        System.out.println("onGameExitMessage");
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