package controllers;

import client.GameClient;
import common.messages.GameExitMessage;
import views.MainFrame;

public class Game {
    public String gameId;

    public Game(String gameId) {
        this.gameId = gameId;
    }

    public void exitGame() {
        System.out.println("Exiting game: " + gameId);
        GameExitMessage message = new GameExitMessage();
        message.gameId = gameId;
        GameClient.instance.client.sendTCP(message);

        MainFrame.instance.showLobbyPanel();
    }
}
