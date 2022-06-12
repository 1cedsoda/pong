package controllers;

import common.messages.GameCreateSignal;

import java.util.ArrayList;
import java.util.List;

public class Games {
    public List<Game> games;

    public Games() {
        this.games = new ArrayList<Game>();
    }

    public Game getByGameId(String gameId) {
        for (Game game : games) {
            if (game.gameId.equals(gameId)) {
                return game;
            }
        }
        return null;
    }

    public void onGameCreateSignal(GameCreateSignal message) {
    }
}