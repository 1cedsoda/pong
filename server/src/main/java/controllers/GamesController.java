package controllers;

import common.controllers.GameController;
import common.messages.GameCreateSignal;

import java.util.ArrayList;
import java.util.List;

public class GamesController {
    public List<GameController> gameControllers;

    public GamesController() {
        this.gameControllers = new ArrayList<GameController>();
    }

    public common.controllers.GameController getByGameId(String gameId) {
        for (common.controllers.GameController gameController : gameControllers) {
            if (gameController.gameId.equals(gameId)) {
                return gameController;
            }
        }
        return null;
    }

    public void onGameCreateSignal(GameCreateSignal message) {
    }
}