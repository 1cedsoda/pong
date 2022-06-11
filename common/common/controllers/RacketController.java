package common.controllers;

import common.models.PlayerModel;
import common.models.RacketModel;

public class RacketController extends RacketModel {
    protected GameController gameController;

    public RacketController(int score, double size, double y, PlayerModel player, GameController gameController) {
        super(score, size, y, player);
        this.gameController = gameController;
    }

    public RacketController(RacketModel racketState, GameController gameController) {
        super(racketState.score, racketState.size, racketState.y, racketState.player);
        this.gameController = gameController;
    }
}
