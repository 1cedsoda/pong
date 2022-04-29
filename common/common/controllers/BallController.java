package common.controllers;

import common.models.BallModel;

public class BallController {
    protected BallModel ballState;

    public BallController(BallModel ballState) {
        this.ballState = ballState;
    }

    public BallModel getBallState() {
        return ballState;
    }
}
