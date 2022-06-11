package common.controllers;

import common.geometry.Point;
import common.models.BallModel;

public class BallController extends BallModel {

    protected GameController gameController;

    public BallController(Point position, double xv, double yv, GameController gameController) {
        super(position, xv, yv);
        this.gameController = gameController;
    }

    public BallController(BallModel ballState, GameController gameController) {
        super(ballState.position, ballState.xv, ballState.yv);
        this.gameController = gameController;
    }
}
