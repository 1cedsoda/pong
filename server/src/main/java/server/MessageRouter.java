package server;

import common.Pair;
import common.controllers.GamesController;
import common.messages.*;
import common.controllers.LobbyController;
import controllers.GameController;

import java.util.List;

public class MessageRouter {
    private GamesController gamesController;
    private LobbyController lobbyController;

    public MessageRouter(GamesController gamesController, LobbyController lobbyController) {
        this.gamesController = gamesController;
        this.lobbyController = lobbyController;
    }

    public void routeMessages(List<ConnectedMessage> messages) {
        for (ConnectedMessage message : messages) {
            if ( message.message instanceof PlayerUpdate) {
                lobbyController.onPlayerUpdate((PlayerUpdate) message.message);
            } else if (message.message instanceof MoveSignal) {
                String gameId = ((MoveSignal) message.message).gameId;
                GameController gameController = (GameController) gamesController.getByGameId(gameId);
                gameController.onMoveSignal((MoveSignal) message.message);
            } else if (message.message instanceof GameJoinSignal) {
                String gameId = ((GameJoinSignal) message.message).gameId;
                GameController gameController = (GameController) gamesController.getByGameId(gameId);
                gameController.onGameJoinSignal((GameJoinSignal) message.message, message.connection);
            } else if (message.message instanceof GameCreateSignal) {
                gamesController.onGameCreateSignal((GameCreateSignal) message.message);
            }
        }
    }
}
