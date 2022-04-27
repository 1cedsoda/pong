package common.controllers;

import common.models.GameModel;

public class GameController {
    protected GameModel gameState;

    public String getGameId() {
        return this.gameState.gameId;
    }
}
