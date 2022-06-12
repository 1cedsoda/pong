package controllers;

import com.esotericsoftware.kryonet.Connection;
import common.messages.GameCreateMessage;

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

    public void onGameCreateSignal(GameCreateMessage message, Connection connection) {
    }
}