package common.controllers;

import common.models.BallModel;
import common.models.GameModel;
import common.models.RacketModel;

public class GameController extends GameModel {
    public GameController(RacketModel leftRacketState, RacketModel rightRacketState, BallModel ballState) {
        super(leftRacketState, rightRacketState, ballState);
    }

    public GameController(GameModel gameState) {
        super(gameState.leftRacketState, gameState.rightRacketState, gameState.ballState);
    }
}
