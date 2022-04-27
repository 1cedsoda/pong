package common.controllers;

import common.messages.GameCreateSignal;

import java.util.ArrayList;
import java.util.List;

public class GamesController {
    public List<GameController> gameControllers;

    public GamesController() {
        this.gameControllers = new ArrayList<GameController>();
    }

    public GameController getByGameId(String gameId) {
        for (GameController gameController : gameControllers) {
            if (gameController.getGameId().equals(gameId)) {
                return gameController;
            }
        }
        return null;
    }

    public void onGameCreateSignal(GameCreateSignal message) {
    }
}
