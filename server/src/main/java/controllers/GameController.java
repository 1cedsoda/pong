package controllers;

import common.messages.GameJoinSignal;
import common.messages.MoveSignal;
import server.Connection;

public class GameController extends common.controllers.GameController {
    private Connection leftConnection;
    private Connection rightConnection;

    public GameController(Connection leftConnection) {
        this.leftConnection = leftConnection;
    }
    public void onMoveSignal(MoveSignal message) {
    }

    public void onGameJoinSignal(GameJoinSignal message, Connection connection) {
        this.rightConnection = connection;
    }
}
