package controllers;

import common.enums.MoveDirection;
import common.messages.GameExitMessage;
import common.messages.GameMoveMessage;
import common.states.GameState;
import networking.GameClient;
import views.MainFrame;

public class Game {
    public String gameId;

    public GameState state;

    public Game(String gameId) {
        this.gameId = gameId;
        this.state = new GameState();
    }

    public void exitGame() {
        System.out.println("Exiting game: " + gameId);
        GameExitMessage message = new GameExitMessage();
        message.gameId = gameId;
        GameClient.instance.client.sendTCP(message);

        MainFrame.instance.showLobbyPanel();
    }

    public void updateState(GameState gameState) {
        this.state = gameState;
        // refresh
        MainFrame.instance.gamePanel.refresh();
    }

    public void move(MoveDirection direction) {
        GameMoveMessage gameMoveMessage = new GameMoveMessage();
        gameMoveMessage.moveDirection = direction;
        GameClient.instance.client.sendTCP(gameMoveMessage);
    }
}
