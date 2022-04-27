package server;

import common.messages.Message;
import common.messages.PlayerUpdate;
import common.controllers.LobbyController;
import controllers.GameController;

import java.util.List;

public class MessageRouter {
    private List<GameController> gameControllers;
    private LobbyController lobbyController;

    public MessageRouter(List<GameController> gameControllers, LobbyController lobbyController) {
        this.gameControllers = gameControllers;
        this.lobbyController = lobbyController;
    }

    public void routeMessages(List<Message> messages) {
        for (Message message : messages) {
            if (PlayerUpdate.class.isInstance(message)) {

            }
        }
    }
}
