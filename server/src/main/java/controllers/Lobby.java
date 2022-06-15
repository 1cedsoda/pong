package controllers;

import com.esotericsoftware.kryonet.Connection;
import common.enums.PlayerActivity;
import common.messages.LobbyJoinMessage;
import common.messages.LobbyStateMessage;
import common.states.LobbyEntryState;
import common.states.LobbyState;
import networking.GameServer;
import networking.PlayerConnection;

import java.util.ArrayList;
import java.util.List;

public class Lobby {

    LobbyState state;
    public Lobby(LobbyState state) {
        this.state = state;
    }

    List<PlayerConnection> connections = new ArrayList<PlayerConnection>();

    public Lobby() {
        this.state = new LobbyState();
        this.state.players = new ArrayList<LobbyEntryState>();
    }

    public void onLobbyJoinMessage(LobbyJoinMessage message, PlayerConnection connection) {
        if (connection.name == null) {
            connection.name = message.name;
        }
        sendLobbyState();
    }

    public void sendLobbyState() {
        this.updateLobbyState();

        LobbyStateMessage lobbyStateMessage = new LobbyStateMessage();
        lobbyStateMessage.lobby = this.state;

        GameServer.instance.server.sendToAllTCP(lobbyStateMessage);
    }

    public void updateLobbyState() {
        this.state.players = new ArrayList<LobbyEntryState>();

        List<Connection> connections = List.of(GameServer.instance.server.getConnections());
        for (Connection connection : connections) {
            PlayerConnection pc = (PlayerConnection) connection;
            LobbyEntryState lobbyEntryState = new LobbyEntryState();
            lobbyEntryState.name = pc.name;
            Game playerGame = GameServer.instance.games.getGameByPlayerConnection(pc);
            if (playerGame != null) {
                lobbyEntryState.gameId = playerGame.state.gameId;
                if (playerGame.isFull()) {
                    lobbyEntryState.activity = PlayerActivity.PLAYING;
                } else {
                    lobbyEntryState.activity = PlayerActivity.WAITING;
                }
            }
            this.state.players.add(lobbyEntryState);
        }
    }
}
