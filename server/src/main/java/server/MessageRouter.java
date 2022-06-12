package server;

import common.messages.*;
import controllers.Game;
import controllers.Games;
import controllers.Lobby;

import java.util.List;

public class MessageRouter {
    private Games games;
    private Lobby lobby;

    public MessageRouter(Games games, Lobby lobby) {
        this.games = games;
        this.lobby = lobby;
    }

    public void routeMessages(List<ConnectedMessage> messages) {
        for (ConnectedMessage message : messages) {
            if ( message.message instanceof PlayerUpdate) {
                //lobby.onPlayerUpdate((PlayerUpdate) message.message);
            } else if (message.message instanceof MoveSignal) {
                String gameId = ((MoveSignal) message.message).gameId;
                Game game = (Game) games.getByGameId(gameId);
                game.onMoveSignal((MoveSignal) message.message);
            } else if (message.message instanceof GameJoinSignal) {
                String gameId = ((GameJoinSignal) message.message).gameId;
                Game game = (Game) games.getByGameId(gameId);
                game.onGameJoinSignal((GameJoinSignal) message.message, message.connection);
            } else if (message.message instanceof GameCreateSignal) {
                games.onGameCreateSignal((GameCreateSignal) message.message);
            }
        }
    }
}
