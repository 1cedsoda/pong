package controllers;

import common.messages.GameJoinSignal;
import common.messages.MoveSignal;
import common.models.GameModel;
import common.models.RacketModel;
import server.Connection;

public class GameController extends common.controllers.GameController {
    private Connection leftConnection;
    private Connection rightConnection;

    private BallController ballController;

    public GameController() {
        super(new GameModel());
        ballController = new BallController(gameState.ballState);

    }
    public void onMoveSignal(MoveSignal message) {
    }

    public void onGameJoinSignal(GameJoinSignal message, Connection connection) {
        this.rightConnection = connection;
    }

    public void onTimerInvocation(long timePassed) {

    }
}
