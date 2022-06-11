package controllers;

import common.messages.GameJoinSignal;
import common.messages.MoveSignal;
import common.models.BallModel;
import common.models.GameModel;
import common.models.RacketModel;
import server.Connection;

public class GameController extends common.controllers.GameController {
    private Connection leftConnection;
    private Connection rightConnection;

    private BallController ballController;

    public GameController(RacketModel leftRacketState, RacketModel rightRacketState, BallModel ballState) {
        super(leftRacketState, rightRacketState, ballState);
        this.ballController = new BallController(ballState, this);
    }
    public void onMoveSignal(MoveSignal message) {
    }

    public void onGameJoinSignal(GameJoinSignal message, Connection connection) {
        this.rightConnection = connection;
    }

    public void onTimerInvocation(long timePassed) {

    }
}
